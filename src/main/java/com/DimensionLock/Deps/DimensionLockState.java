package com.DimensionLock.Deps;

/**
 * Stores the current runtime lock state for Nether and End.
 * Initializes values based on the server config.
 */
public class DimensionLockState {

    private static boolean nether = Config.LOCK_NETHER.get();
    private static boolean end = Config.LOCK_END.get();

    // ---- GETTERS ----
    public static boolean isNetherLocked() {
        return nether;
    }

    public static boolean isEndLocked() {
        return end;
    }

    // ---- SETTERS ----
    public static void setNether(boolean value) {
        nether = value;
    }

    public static void setEnd(boolean value) {
        end = value;
    }

    // ---- TOGGLES ----
    public static void toggleNether() {
        nether = !nether;
    }

    public static void toggleEnd() {
        end = !end;
    }

    // ---- RELOAD FROM CONFIG ----
    public static void reloadFromConfig() {
        nether = Config.LOCK_NETHER.get();
        end = Config.LOCK_END.get();
    }
}
