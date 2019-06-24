package com.extracraftx.minecraft.serveradditionsutil.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface ClientItemStackProvider{

    public ItemStack getClientItemStack(ItemStack original);
    public Identifier getId();
    
}