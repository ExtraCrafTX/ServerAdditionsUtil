package com.extracraftx.minecraft.serveradditionsutil.interfaces;

import net.minecraft.recipe.Recipe;

public interface ClientRecipeProvider<T extends Recipe<?>> {

    public T getClientRecipe(T original);

}