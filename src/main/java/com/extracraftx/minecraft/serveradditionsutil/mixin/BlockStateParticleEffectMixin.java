package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.BlockStateParticleEffect;

@Mixin(BlockStateParticleEffect.class)
public abstract class BlockStateParticleEffectMixin{

    @ModifyArg(method="write", at=@At(value="INVOKE",target="Lnet/minecraft/util/IdList;getId(Ljava/lang/Object;)I"), index=0)
    private Object modifyBlockState(Object object){
        BlockState original = (BlockState) object;
        Block block = original.getBlock();
        if(block instanceof ClientBlockStateProvider){
            return ((ClientBlockStateProvider)block).getClientBlockState(original);
        }
        return original;
    }

}