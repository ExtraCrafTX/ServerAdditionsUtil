package com.extracraftx.minecraft.serveradditionsutil.mixin;

import java.util.Iterator;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.packet.BlockBreakingProgressS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World{

    public ServerWorldMixin(){
        super(null, null, null, null, false);
    }

    @Inject(method = "setBlockBreakingProgress", at = @At(value="INVOKE", shift = Shift.BY, by = 3, target = "Ljava/util/Iterator;next()Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onSetBlockBreakingProgress(int entityId, BlockPos pos, int progress, CallbackInfo info, Iterator iterator, ServerPlayerEntity player){
        if(player != null && player.world == this && player.getEntityId() == entityId){
            BlockState blockState = getBlockState(pos);
            Block block = blockState.getBlock();
            if(block instanceof ClientBlockStateProvider){
                player.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(entityId, pos, progress));
            }
        }
    }

}