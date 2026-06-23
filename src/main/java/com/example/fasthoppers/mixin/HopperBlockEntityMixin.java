package com.example.fasthoppers.mixin;

import com.example.fasthoppers.FastHoppersMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Replaces the hopper's once-per-8-ticks single-item transfer with a burst:
 * each tick it runs the vanilla insert+extract up to ITEMS_PER_TICK times and
 * keeps the cooldown at 0 so it never has to wait.
 *
 * serverTick is the hopper's tick method. We cancel it and drive the transfer
 * ourselves, calling vanilla's own insertAndExtract / extract so all the normal
 * rules (filters, item sorting, locked slots, etc.) still apply per item.
 */
@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {

    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
    private static void fasthoppers$burstTransfer(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, CallbackInfo ci) {
        for (int i = 0; i < FastHoppersMod.ITEMS_PER_TICK; i++) {
            boolean moved = HopperBlockEntityInvoker.fasthoppers$invokeInsertAndExtract(
                    world, pos, state, blockEntity,
                    () -> HopperBlockEntity.extract(world, blockEntity));
            // Stop early once nothing more can move this tick.
            if (!moved) {
                break;
            }
        }

        // Never sit on a cooldown — transfer again next tick.
        ((HopperBlockEntityAccessor) blockEntity).fasthoppers$setTransferCooldown(0);

        // We fully handled this tick, so skip the vanilla single transfer.
        ci.cancel();
    }
}
