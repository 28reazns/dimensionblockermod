package com.DimensionLock.Deps;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOCK_NETHER = BUILDER
            .comment("Whether the Nether is locked by default")
            .define("lockNether", true);

    public static final ModConfigSpec.BooleanValue LOCK_END = BUILDER
            .comment("Whether the End is locked by default")
            .define("lockEnd", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
