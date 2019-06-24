package com.extracraftx.minecraft.serveradditionsutil.interfaces;

@FunctionalInterface
public interface Vanillifier<T>{
    T vanillify(T original);
}