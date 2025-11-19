package com.DimensionLock.Deps;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

/**
 * Main mod class for DimensionLock.
 * Handles registration of config and event listeners.
 */
@Mod(DimensionLockMod.MODID)
public class DimensionLockMod {

    public static final String MODID = "dimensionlock";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DimensionLockMod(IEventBus modEventBus, ModContainer modContainer) {

        // Register common setup
        modEventBus.addListener(this::commonSetup);

        // Register config file (server config)
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);

        // Register this class to handle server events
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("[DimensionLock] Common setup initialized.");

        LOGGER.info("[DimensionLock] Default config values: Nether locked = {}, End locked = {}",
                Config.LOCK_NETHER.get(), Config.LOCK_END.get());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("[DimensionLock] Server is starting...");

        LOGGER.info("[DimensionLock] Current Lock State: Nether = {}, End = {}",
                DimensionLockState.isNetherLocked(),
                DimensionLockState.isEndLocked());
    }

}
