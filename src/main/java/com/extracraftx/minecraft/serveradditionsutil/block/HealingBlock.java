package com.extracraftx.minecraft.serveradditionsutil.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HealingBlock extends ServerSideBlock {

    public HealingBlock(Identifier id, Settings settings) {
        super(Blocks.SPONGE, id, settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity,
            ItemStack stack) {
        world.getBlockTickScheduler().schedule(pos, this, 5);
    }

    @Override
    public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
        super.onScheduledTick(state, world, pos, random);
        if(!world.isClient){
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.spawnParticles(ParticleTypes.HEART, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 1, 0.5, 0.5, 0.5, 1);
        }
        world.getBlockTickScheduler().schedule(pos, this, 5);
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockHitResult hitResult) {
        if(!world.isClient){
            if(!world.getBlockTickScheduler().isScheduled(pos, this))
                world.getBlockTickScheduler().schedule(pos, this, 5);
            if(player.getHealth() < player.getHealthMaximum()){
                player.heal(1);
                ServerWorld sw = (ServerWorld)world;
                sw.spawnParticles(ParticleTypes.EFFECT, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 5, 0.5, 0.5, 0.5, 10);
                return true;
            }
            if(player.getHungerManager().isNotFull()){
                player.getHungerManager().add(1, 0);
                return true;
            }
        }
        return false;
    }



}