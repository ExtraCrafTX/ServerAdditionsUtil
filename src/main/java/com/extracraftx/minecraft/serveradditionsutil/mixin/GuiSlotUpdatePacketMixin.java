package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.packet.GuiSlotUpdateS2CPacket;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(GuiSlotUpdateS2CPacket.class)
public abstract class GuiSlotUpdatePacketMixin{

    @Shadow
    private ItemStack itemStack;

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        Item item = this.itemStack.getItem();
        if(item instanceof ClientItemStackProvider){
            this.itemStack = ((ClientItemStackProvider)item).getClientItemStack(this.itemStack);
        }
    }

}