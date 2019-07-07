package com.extracraftx.minecraft.serveradditionsutil.item;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.Vanillifier;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Identifier;

public class ServerSideItem extends Item implements ClientItemStackProvider {

    private Vanillifier<ItemStack> vanillifier;
    private final Identifier id;
    private final String name;

    /**
     * Creates an item with the given identifier and item settings which will be
     * vanillified using the given vanillifier
     * 
     * @param id          The identifier of the block item
     * @param settings    The item settings
     * @param vanillifier The vanillifier to provide item stacks for clients
     */
    public ServerSideItem(Identifier id, Item.Settings settings, Vanillifier<ItemStack> vanillifier) {
        super(settings);
        this.vanillifier = vanillifier;
        this.id = id;
        this.name = null;
    }

    /**
     * Creates an item with the given identifier and item settings which will be
     * vanillified using the given vanillifier
     * 
     * @param id          The identifier of the block item
     * @param settings    The item settings
     * @param vanillifier The vanillifier to provide item stacks for clients
     * @param name        The name to be displayed for this item
     */
    public ServerSideItem(Identifier id, Item.Settings settings, Vanillifier<ItemStack> vanillifier, String name) {
        super(settings);
        this.vanillifier = vanillifier;
        this.id = id;
        this.name = name;
    }

    /**
     * Creates an item with the given identifier and item settings. Creates a
     * default vanillifier that takes the incoming stack and copies its count to a
     * stack of the representing item and gives it a custom name
     * 
     * @param block    The block to create a block item for
     * @param id       The identifier of the block item
     * @param settings The item settings
     */
    public ServerSideItem(Item representation, Identifier id, Item.Settings settings) {
        super(settings);
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
     * Creates an item with the given identifier and item settings. Creates a
     * default vanillifier that takes the incoming stack and copies its count to a
     * stack of the representing item and gives it a custom name
     * 
     * @param block    The block to create a block item for
     * @param id       The identifier of the block item
     * @param settings The item settings
     * @param name     The name to be displayed for this item
     */
    public ServerSideItem(Item representation, Identifier id, Item.Settings settings, String name) {
        super(settings);
        this.id = id;
        vanillifier = (original) -> {
            ItemStack changed = new ItemStack(representation);
            changed.setCount(original.getCount());
            changed.setCustomName(new TextComponent(I18n.translate(getName(original).getText()))
                    .modifyStyle(style -> style.setItalic(false)).applyFormat(getRarity(original).formatting));
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