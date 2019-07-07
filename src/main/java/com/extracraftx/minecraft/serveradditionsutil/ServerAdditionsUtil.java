package com.extracraftx.minecraft.serveradditionsutil;

import com.extracraftx.minecraft.serveradditionsutil.block.HealingBlock;
import com.extracraftx.minecraft.serveradditionsutil.item.ServerSideBlockItem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ServerAdditionsUtil implements ModInitializer {

    @Override
    public void onInitialize() {
        Identifier healingBlockId = new Identifier("serveradditionsutil", "healing_block");
        HealingBlock healingBlock = new HealingBlock(healingBlockId, FabricBlockSettings.copy(Blocks.SPONGE).build());
        Registry.register(Registry.BLOCK, healingBlockId, healingBlock);
        Registry.register(Registry.ITEM, healingBlockId, new ServerSideBlockItem(healingBlock, healingBlockId,
                new Item.Settings().group(ItemGroup.MISC).maxCount(16), "Healing Block"));
    }

}