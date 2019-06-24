package com.extracraftx.minecraft.serveradditionsutil.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;

public interface ClonableItemStackConsumer<T extends Recipe<?>>{
    
    public T clone(T original, ItemStack itemStack);

}