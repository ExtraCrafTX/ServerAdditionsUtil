package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;

@Environment(EnvType.CLIENT)
@Mixin(ItemStackParticleEffect.class)
public abstract class ItemStackParticleEffectClientMixin{

    @Inject(method="getItemStack",at=@At("RETURN"),cancellable=true)
    private void onGetItemStack(CallbackInfoReturnable<ItemStack> info){
        ItemStack original = info.getReturnValue();
        Item item = original.getItem();
        if(item instanceof ClientItemStackProvider){
            info.setReturnValue(((ClientItemStackProvider)item).getClientItemStack(original));
        }
    }

}