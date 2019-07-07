package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

@Mixin(Ingredient.class)
public abstract class IngredientMixin{

    @ModifyArg(method="write", at=@At(value="INVOKE", target="Lnet/minecraft/util/PacketByteBuf;writeItemStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/PacketByteBuf;"))
    private ItemStack modifyItemStack(ItemStack original){
        Item item = original.getItem();
        if(item instanceof ClientItemStackProvider){
            return ((ClientItemStackProvider)item).getClientItemStack(original);
        }
        return original;
    }

}