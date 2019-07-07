package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientRecipeProvider;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin implements ClientRecipeProvider<ShapedRecipe>{
    @Shadow @Final
    private int width;
    @Shadow @Final
    private int height;
    @Shadow @Final
    private DefaultedList<Ingredient> inputs;
    @Shadow @Final
    private ItemStack output;
    @Shadow @Final
    private Identifier id;
    @Shadow @Final
    private String group;

    @Override
    public ShapedRecipe getClientRecipe() {
        ItemStack output = this.output;
        Item outputItem = output.getItem();
        if(outputItem instanceof ClientItemStackProvider){
            output = ((ClientItemStackProvider)outputItem).getClientItemStack(output);
        }
        return new ShapedRecipe(id, group, width, height, inputs, output);
    }
}