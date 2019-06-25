package com.extracraftx.minecraft.serveradditionsutil.interfaces;

@FunctionalInterface
public interface Vanillifier<T> {
    /**
     * Convert the given object into a vanilla compatible representation
     * 
     * @param original
     * @return A vanilla compatible representation that can be sent to clients
     */
    T vanillify(T original);
}