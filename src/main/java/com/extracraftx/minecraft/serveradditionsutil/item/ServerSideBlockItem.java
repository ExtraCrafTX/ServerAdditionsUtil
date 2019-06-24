package com.extracraftx.minecraft.serveradditionsutil.item;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientBlockStateProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.Vanillifier;

import net.minecraft.block.Block;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;

public class ServerSideBlockItem extends BlockItem implements ClientItemStackProvider {

    private Vanillifier<ItemStack> vanillifier;
    private final Identifier id;

    public ServerSideBlockItem(Block block, Identifier id, Item.Settings settings,
            Vanillifier<ItemStack> itemStackSupplier) {
        super(block, settings);
        this.vanillifier = itemStackSupplier;
        this.id = id;
    }

    public <T extends Block & ClientBlockStateProvider> ServerSideBlockItem(T block, Identifier id, Item.Settings settings) {
        super(block, settings);
        Item representation = block.getDefaultClientBlockState().getBlock().asItem();
        this.id = id;
        vanillifier = (original) -> {
            ItemStack changed = new ItemStack(representation);
            changed.setCount(original.getCount());
            changed.setCustomName(new TextComponent(I18n.translate(getName(original).getText())));
            return changed;
        };
    }

    @Override
    public ItemStack getClientItemStack(ItemStack original) {
        return vanillifier.vanillify(original);
    }

    @Override
    public Identifier getId() {
        return id;
    }

}