package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.packet.BlockUpdateS2CPacket;

@Mixin(BlockUpdateS2CPacket.class)
public abstract class BlockUpdatePacketMixin{

    @Shadow
    private BlockState state;

    @Inject(method = "<init>(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)V", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        Block block = this.state.getBlock();
        if(block instanceof ClientBlockStateProvider){
            this.state = ((ClientBlockStateProvider)block).getClientBlockState(this.state);
        }
    }

}