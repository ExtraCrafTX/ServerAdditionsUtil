package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientItemStackProvider;
import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientTradeOfferProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.village.TradeOffer;

@Mixin(TradeOffer.class)
public abstract class TradeOfferMixin implements ClientTradeOfferProvider{
    
    @Override
    public TradeOffer getClientTradeOffer() {
        CompoundTag tag = this.toTag();
        ItemStack buy = convertStack(ItemStack.fromTag(tag.getCompound("buy")));
        buy.toTag(tag.getCompound("buy"));
        ItemStack buyB = convertStack(ItemStack.fromTag(tag.getCompound("buyB")));
        buyB.toTag(tag.getCompound("buyB"));
        ItemStack sell = convertStack(ItemStack.fromTag(tag.getCompound("sell")));
        sell.toTag(tag.getCompound("sell"));
        return new TradeOffer(tag);
    }

    private ItemStack convertStack(ItemStack original){
        Item item = original.getItem();
        if(item instanceof ClientItemStackProvider){
            return ((ClientItemStackProvider)item).getClientItemStack(original);
        }
        return original;
    }

    @Shadow
    public CompoundTag toTag(){
        return null;
    }
    
}