package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClonableItemStackConsumer;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin implements ClonableItemStackConsumer<ShapedRecipe>{
    @Override
    public ShapedRecipe clone(ShapedRecipe original, ItemStack itemStack) {
        return new ShapedRecipe(original.getId(), original.getGroup(), original.getWidth(), original.getHeight(), original.getPreviewInputs(), itemStack);
    }
}