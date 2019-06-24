package com.extracraftx.minecraft.serveradditionsutil.block;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.Vanillifier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class ServerSideBlock extends Block implements ClientBlockStateProvider {

    Vanillifier<BlockState> vanillifier;
    Identifier id;

    public ServerSideBlock(Block representation, Identifier id, Settings settings) {
        super(settings);
        this.vanillifier = original -> representation.getDefaultState();
        this.id = id;
    }

    public ServerSideBlock(Vanillifier<BlockState> vanillifier, Identifier id, Settings settings){
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