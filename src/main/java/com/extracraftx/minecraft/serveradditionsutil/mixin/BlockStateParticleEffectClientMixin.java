package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.BlockStateParticleEffect;

@Environment(EnvType.CLIENT)
@Mixin(BlockStateParticleEffect.class)
public abstract class BlockStateParticleEffectClientMixin{

    @Inject(method="getBlockState", at=@At("RETURN"), cancellable=true)
    private void onGetBlockState(CallbackInfoReturnable<BlockState> info){
        BlockState original = info.getReturnValue();
        Block block = original.getBlock();
        if(block instanceof ClientBlockStateProvider){
            info.setReturnValue(((ClientBlockStateProvider)block).getClientBlockState(original));
        }
    }

}