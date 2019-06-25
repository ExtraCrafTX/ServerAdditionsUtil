package com.extracraftx.minecraft.serveradditionsutil.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public interface ClientBlockStateProvider {

    /**
     * Should get a vanilla block state that will be sent to the client instead of
     * the given modded block state
     * 
     * @param original
     * @return Vanillified block state
     */
    public BlockState getClientBlockState(BlockState original);

    /**
     * Convenience method for getting a vanilla block state for the default block
     * state of the block
     * 
     * @return Vanillified default block state
     */
    public BlockState getDefaultClientBlockState();

    /**
     * Get the identifier that represents this block
     * 
     * @return
     */
    public Identifier getId();

}