package com.extracraftx.minecraft.serveradditionsutil.mixin;

import java.util.List;

import com.extracraftx.minecraft.serveradditionsutil.interfaces.ClientRecipeProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.packet.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;

@Mixin(SynchronizeRecipesS2CPacket.class)
public abstract class SynchronizeRecipesPacketMixin{

    @Shadow
    private List<Recipe<?>> recipes;

    @Inject(method = "<init>(Ljava/util/Collection;)V", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        for(int i = 0; i < recipes.size(); i++){
            Recipe<?> recipe = recipes.get(i);
            if(recipe instanceof ClientRecipeProvider){
                recipes.set(i, ((ClientRecipeProvider)recipe).getClientRecipe(recipe));
            }
        }
    }

}