package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin{

    @Redirect(method="toHoverableText",at=@At(value="INVOKE",target="Lnet/minecraft/item/ItemStack;toTag(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;"))
    private CompoundTag toTagProxy(ItemStack original, CompoundTag out){
        Item item = this.getItem();
        if(item instanceof ClientItemStackProvider){
            return ((ClientItemStackProvider)item).getClientItemStack(original).toTag(out);
        }
        return original.toTag(out);
    }

    @Shadow
    public Item getItem(){
        return null;
    }

}