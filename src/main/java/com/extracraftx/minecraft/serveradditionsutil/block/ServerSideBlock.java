package com.extracraftx.minecraft.serveradditionsutil.block;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.Vanillifier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class ServerSideBlock extends Block implements ClientBlockStateProvider {

    Vanillifier<BlockState> vanillifier;
    Identifier id;

    /**
     * Creates a server side block which is represented by the given block. Creates
     * a default vanillifier that simply uses the default state of the supplied
     * block
     * 
     * @param representation The block which will represnt this block
     * @param id             The identifier of this block
     * @param settings       The block settings
     */
    public ServerSideBlock(Block representation, Identifier id, Settings settings) {
        super(settings);
        this.vanillifier = original -> representation.getDefaultState();
        this.id = id;
    }

    /**
     * Creates a server side block whose block states are converted with the
     * vanillifier given
     * 
     * @param vanillifier The vanillifier to convert block states to client block
     *                    states
     * @param id          The identifier of this block
     * @param settings    The block settings
     */
    public ServerSideBlock(Vanillifier<BlockState> vanillifier, Identifier id, Settings settings) {
        super(settings);
        this.vanillifier = vanillifier;
        this.id = id;
    }

    @Override
    public BlockState getClientBlockState(BlockState original) {
        return vanillifier.vanillify(original);
    }

    @Override
    public BlockState getDefaultClientBlockState() {
        return vanillifier.vanillify(getDefaultState());
    }

    @Override
    public Identifier getId() {
        return id;
    }

}