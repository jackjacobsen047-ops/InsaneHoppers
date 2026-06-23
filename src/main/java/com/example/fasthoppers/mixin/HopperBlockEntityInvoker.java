package com.example.fasthoppers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BooleanSupplier;

/**
 * Exposes the private static HopperBlockEntity.insertAndExtract(...) so the
 * mixin can call it directly. Static @Invoker methods need a dummy body — Mixin
 * replaces it at load time.
 */
@Mixin(HopperBlockEntity.class)
public interface HopperBlockEntityInvoker {

    @Invoker("insertAndExtract")
    static boolean fasthoppers$invokeInsertAndExtract(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, BooleanSupplier booleanSupplier) {
        throw new AssertionError("Mixin did not apply — this body should never run.");
    }
}
