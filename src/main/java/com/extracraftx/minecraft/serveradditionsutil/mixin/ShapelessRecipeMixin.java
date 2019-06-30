package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClonableItemStackConsumer;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapelessRecipe;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin implements ClonableItemStackConsumer<ShapelessRecipe>{
    @Override
    public ShapelessRecipe clone(ShapelessRecipe original, ItemStack itemStack) {
        return new ShapelessRecipe(original.getId(), original.getGroup(), itemStack, original.getPreviewInputs());
    }
}