package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.FallingBlockEntity;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin{

    @ModifyArg(method = "createSpawnPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getRawIdFromState(Lnet/minecraft/block/BlockState;)I"), index=0)
    private BlockState modifyBlockState(BlockState original){
        Block block = original.getBlock();
        if(block instanceof ClientBlockStateProvider){
            return ((ClientBlockStateProvider)block).getClientBlockState(original);
        }
        return original;
    }

}