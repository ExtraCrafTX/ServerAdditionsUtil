package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;

@Mixin(ItemStackParticleEffect.class)
public abstract class ItemStackParticleEffectMixin{

    @ModifyArg(method="write",at=@At(value="INVOKE",target="Lnet/minecraft/util/PacketByteBuf;writeItemStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/PacketByteBuf;"),index=0)
    private ItemStack modivItemStack(ItemStack original){
        Item item = original.getItem();
        if(item instanceof ClientItemStackProvider){
            return ((ClientItemStackProvider)item).getClientItemStack(original);
        }
        return original;
    }

}