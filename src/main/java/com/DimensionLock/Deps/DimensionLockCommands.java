package com.DimensionLock.Deps;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DimensionLockMod.MODID)
public class DimensionLockCommands {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {

        event.getDispatcher().register(
                Commands.literal("dimensionlock")
                        .requires(source -> source.hasPermission(2))

                        .then(Commands.literal("toggle")
                                .then(Commands.argument("target", StringArgumentType.string())
                                        .executes(ctx -> {
                                            String t = StringArgumentType.getString(ctx, "target");
                                            return toggle(ctx.getSource(), t);
                                        })
                                )
                                .executes(ctx -> toggle(ctx.getSource(), "all"))
                        )

                        .then(Commands.literal("set")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .then(Commands.argument("target", StringArgumentType.string())
                                                .executes(ctx -> {
                                                    boolean v = BoolArgumentType.getBool(ctx, "value");
                                                    String t = StringArgumentType.getString(ctx, "target");
                                                    return set(ctx.getSource(), t, v);
                                                })
                                        ))
                        )
        );
    }

    private static int toggle(net.minecraft.commands.CommandSourceStack src, String target) {
        switch (target.toLowerCase()) {
            case "nether" -> DimensionLockState.toggleNether();
            case "end" -> DimensionLockState.toggleEnd();
            default -> {
                DimensionLockState.toggleNether();
                DimensionLockState.toggleEnd();
            }
        }
        src.sendSuccess(() -> Component.literal("Toggled " + target), true);
        return 1;
    }

    private static int set(net.minecraft.commands.CommandSourceStack src, String target, boolean value) {
        switch (target.toLowerCase()) {
            case "nether" -> DimensionLockState.setNether(value);
            case "end" -> DimensionLockState.setEnd(value);
            default -> {
                DimensionLockState.setNether(value);
                DimensionLockState.setEnd(value);
            }
        }
        src.sendSuccess(() -> Component.literal("Set " + target + " to " + value), true);
        return 1;
    }
}
