package com.extracraftx.minecraft.serveradditionsutil.mixin;

import java.util.List;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.packet.EntityTrackerUpdateS2CPacket;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(EntityTrackerUpdateS2CPacket.class)
public abstract class EntityTrackerUpdatePacketMixin{

    @Shadow
    private List<DataTracker.Entry<?>> trackedValues;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        if(trackedValues == null)
            return;
        for(int i = 0; i < trackedValues.size(); i++){
            DataTracker.Entry entry = trackedValues.get(i);
            Object value = entry.get();
            if(value instanceof ItemStack){
                ItemStack itemStack = (ItemStack)value;
                Item item = itemStack.getItem();
                if(item instanceof ClientItemStackProvider){
                    ItemStack changed = ((ClientItemStackProvider)item).getClientItemStack(itemStack);
                    DataTracker.Entry updated = entry.copy();
                    updated.set(changed);
                    trackedValues.set(i,updated);
                }
            }
        }
    }

}