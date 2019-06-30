package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientRecipeProvider;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin implements ClientRecipeProvider<ShapelessRecipe>{
    @Override
    public ShapelessRecipe getClientRecipe(ShapelessRecipe original) {
        ItemStack output = original.getOutput();
        Item outputItem = output.getItem();
        if(outputItem instanceof ClientItemStackProvider){
            output = ((ClientItemStackProvider)outputItem).getClientItemStack(output);
        }
        DefaultedList<Ingredient> ingredients = original.getPreviewInputs();
        DefaultedList<Ingredient> transformedIngredients = DefaultedList.create();
        for(Ingredient ingredient : ingredients){
            ItemStack[] originalStacks = ingredient.getStackArray();
            ItemStack[] transformedStacks = new ItemStack[originalStacks.length];
            for(int i = 0; i < originalStacks.length; i++){
                ItemStack stack = originalStacks[i];
                Item item = stack.getItem();
                if(item instanceof ClientItemStackProvider){
                    transformedStacks[i] = ((ClientItemStackProvider)item).getClientItemStack(stack);
                }else{
                    transformedStacks[i] = stack;
                }
            }
            transformedIngredients.add(Ingredient.ofStacks(transformedStacks));
        }
        return new ShapelessRecipe(original.getId(), original.getGroup(), output, transformedIngredients);
    }
}