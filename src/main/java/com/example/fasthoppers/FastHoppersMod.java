package com.example.fasthoppers;

import net.fabricmc.api.ModInitializer;

public class FastHoppersMod implements ModInitializer {

    /**
     * How many items a hopper will try to move every single game tick.
     * Vanilla effectively moves 1 item every 8 ticks, so 24 here is a huge
     * speed-up. Lower this number if it's too fast or causes lag, then rebuild.
     */
    public static final int ITEMS_PER_TICK = 24;

    @Override
    public void onInitialize() {
        // Behaviour is applied entirely by the mixins (see the mixin package).
    }
}
