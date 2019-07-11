package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.packet.ChunkDeltaUpdateS2CPacket;

@Mixin(ChunkDeltaUpdateS2CPacket.ChunkDeltaRecord.class)
public abstract class ChunkDeltaRecordMixin{

    @Inject(method = "getState", at = @At("RETURN"), cancellable = true)
    private void onGetState(CallbackInfoReturnable<BlockState> info){
        BlockState original = info.getReturnValue();
        Block block = original.getBlock();
        if(block instanceof ClientBlockStateProvider)
            info.setReturnValue(((ClientBlockStateProvider)block).getClientBlockState(original));
    }

}