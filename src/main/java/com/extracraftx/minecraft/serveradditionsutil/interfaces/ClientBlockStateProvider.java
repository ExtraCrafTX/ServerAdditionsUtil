package com.extracraftx.minecraft.serveradditionsutil.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public interface ClientBlockStateProvider{

    public BlockState getClientBlockState(BlockState original);
    public BlockState getDefaultClientBlockState();
    public Identifier getId();

}