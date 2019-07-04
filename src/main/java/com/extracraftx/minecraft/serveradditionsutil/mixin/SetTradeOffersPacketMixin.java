package com.extracraftx.minecraft.serveradditionsutil.mixin;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientTradeOfferProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.packet.SetTradeOffersPacket;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TraderOfferList;

@Mixin(SetTradeOffersPacket.class)
public abstract class SetTradeOffersPacketMixin{

    @Shadow
    private TraderOfferList recipes;

    @Inject(method = "<init>", at=@At("RETURN"))
    private void onInit(CallbackInfo info){
        if(recipes == null)
            return;
        TraderOfferList clientRecipes = new TraderOfferList();
        for(TradeOffer original : recipes){
            clientRecipes.add(((ClientTradeOfferProvider)original).getClientTradeOffer(original));
        }
        recipes = clientRecipes;
    }

}