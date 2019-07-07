package com.extracraftx.minecraft.serveradditionsutil.item;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.Vanillifier;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;

public class ServerSideBlockItem extends BlockItem implements ClientItemStackProvider {

    private Vanillifier<ItemStack> vanillifier;
    private final Identifier id;
    private String name;

    /**
     * Creates a block item for the supplied block with the given identifier and
     * item settings which will be vanillified using the given vanillifier
     * 
     * @param block       The block to create a block item for
     * @param id          The identifier of the block item
     * @param settings    The item settings
     * @param vanillifier The vanillifier to provide item stacks for clients
     */
    public ServerSideBlockItem(Block block, Identifier id, Item.Settings settings, Vanillifier<ItemStack> vanillifier) {
        super(block, settings);
        this.vanillifier = vanillifier;
        this.id = id;
        this.name = null;
    }

    /**
     * Creates a block item for the supplied block with the given identifier and
     * item settings which will be vanillified using the given vanillifier
     * 
     * @param block       The block to create a block item for
     * @param id          The identifier of the block item
     * @param settings    The item settings
     * @param vanillifier The vanillifier to provide item stacks for clients
     * @param name        The name to be displayed for this item
     */
    public ServerSideBlockItem(Block block, Identifier id, Item.Settings settings, Vanillifier<ItemStack> vanillifier,
            String name) {
        super(block, settings);
        this.vanillifier = vanillifier;
        this.id = id;
        this.name = name;
    }

    /**
     * Creates a block item for the supplied block with the given identifier and
     * item settings. The block must also provide client block states. Creates a
     * default vanillifier that takes the incoming stack and copies its count to a
     * stack with the representation of the given block and gives it a custom name
     * 
     * @param block    The block to create a block item for
     * @param id       The identifier of the block item
     * @param settings The item settings
     */
    public <T extends Block & ClientBlockStateProvider> ServerSideBlockItem(T block, Identifier id,
            Item.Settings settings) {
        super(block, settings);
        Item representation = block.getDefaultClientBlockState().getBlock().asItem();
        this.id = id;
        vanillifier = (original) -> {
            ItemStack changed = new ItemStack(representation);
            changed.setCount(original.getCount());
            changed.setCustomName(getName(original).modifyStyle(style -> style.setItalic(false))
                    .applyFormat(getRarity(original).formatting));
            return changed;
        };
        this.name = null;
    }

    /**
     * Creates a block item for the supplied block with the given identifier and
     * item settings. The block must also provide client block states. Creates a
     * default vanillifier that takes the incoming stack and copies its count to a
     * stack with the representation of the given block and gives it a custom name
     * 
     * @param block    The block to create a block item for
     * @param id       The identifier of the block item
     * @param settings The item settings
     * @param name     The name to be displayed for this item
     */
    public <T extends Block & ClientBlockStateProvider> ServerSideBlockItem(T block, Identifier id,
            Item.Settings settings, String name) {
        super(block, settings);
        Item representation = block.getDefaultClientBlockState().getBlock().asItem();
        this.id = id;
        vanillifier = (original) -> {
            ItemStack changed = new ItemStack(representation);
            changed.setCount(original.getCount());
            changed.setCustomName(getName(original).modifyStyle(style -> style.setItalic(false))
                    .applyFormat(getRarity(original).formatting));
            return changed;
        };
        this.name = name;
    }

    @Override
    public ItemStack getClientItemStack(ItemStack original) {
        return vanillifier.vanillify(original);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (name == null)
            return super.getName(stack);
        else
            return new TextComponent(name);
    }

}