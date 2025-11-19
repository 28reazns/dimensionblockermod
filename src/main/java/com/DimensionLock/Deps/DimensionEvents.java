package com.DimensionLock.Deps;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = DimensionLockMod.MODID)
public class DimensionEvents {

    private static final ResourceKey<Level> NETHER =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("minecraft:the_nether"));

    private static final ResourceKey<Level> END =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("minecraft:the_end"));


    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        ResourceKey<Level> to = event.getTo();

        if (to.equals(NETHER) && DimensionLockState.isNetherLocked()) {
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Nether is locked."));
            player.teleportTo(player.serverLevel(), player.getX(), player.getY(), player.getZ(),
                    player.getYRot(), player.getXRot());
        }

        if (to.equals(END) && DimensionLockState.isEndLocked()) {
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal("End is locked."));
            player.teleportTo(player.serverLevel(), player.getX(), player.getY(), player.getZ(),
                    player.getYRot(), player.getXRot());
        }
    }
}
