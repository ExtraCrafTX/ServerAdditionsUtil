package com.extracraftx.minecraft.serveradditionsutil.mixin;

import java.util.List;
import java.util.Set;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.util.version.VersionParsingException;
import net.minecraft.SharedConstants;

public class Plugin implements IMixinConfigPlugin {

    String thresholdVersion = "1.14.2";
    boolean isOld = false;

    @Override
    public void onLoad(String mixinPackage) {
        try{
            SemanticVersion old = SemanticVersion.parse(thresholdVersion);
            String version = SharedConstants.getGameVersion().getId();
            version = version.split(" ")[0];
            SemanticVersion mc = SemanticVersion.parse(version);
            isOld = mc.compareTo(old) <= 0;
        }catch(VersionParsingException e){
            System.err.println("MC version is weird, defaulting to new mixin");
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        mixinClassName = mixinClassName.replace("com.extracraftx.minecraft.serveradditionsutil.mixin.", "");
        if(mixinClassName.startsWith("SetTradeOffersPacket")){
            if(mixinClassName.endsWith("New") && !isOld){
                return true;
            }
            return isOld;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        //Do nothing
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //Do nothing
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //Do nothing
    }

}