package com.example.fasthoppers.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Lets us write the hopper's private transferCooldown field so we can keep it
 * at 0 (no waiting between transfers).
 */
@Mixin(HopperBlockEntity.class)
public interface HopperBlockEntityAccessor {

    @Accessor("transferCooldown")
    void fasthoppers$setTransferCooldown(int value);
}
