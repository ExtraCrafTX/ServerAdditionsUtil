package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.packet.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;

@Mixin(ChunkDeltaUpdateS2CPacket.ChunkDeltaRecord.class)
public abstract class ChunkDeltaRecordMixin{

    @Redirect(method = "Lnet/minecraft/client/network/packet/ChunkDeltaUpdateS2CPacket/ChunkDeltaRecord;<init>(SLnet/minecraft/world/chunk/WorldChunk;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/WorldChunk;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"))
    private BlockState getBlockStateProxy(WorldChunk chunk, BlockPos pos){
        BlockState actual = chunk.getBlockState(pos);
        Block block = actual.getBlock();
        if(block instanceof ClientBlockStateProvider){
            return ((ClientBlockStateProvider)block).getClientBlockState(actual);
        }
        return actual;
    }

}