package com.extracraftx.minecraft.serveradditionsutil.mixin;

import java.util.List;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.packet.InventoryS2CPacket;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(InventoryS2CPacket.class)
public abstract class InventoryPacketMixin{

    @Shadow
    private List<ItemStack> slotStackList;

    @Inject(method = "<init>", at= @At("RETURN"))
    private void onInit(CallbackInfo info){
        if(slotStackList == null)
            return;
        for(int i = 0; i < slotStackList.size(); i++){
            ItemStack original = slotStackList.get(i);
            Item item = original.getItem();
            if(item instanceof ClientItemStackProvider){
                slotStackList.set(i, ((ClientItemStackProvider)item).getClientItemStack(original));
            }
        }
    }

}