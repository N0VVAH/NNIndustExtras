package net.torchednova.nnindustextras.mixin;

import appeng.api.networking.IGrid;
import appeng.api.networking.crafting.ICraftingPlan;
import appeng.api.networking.crafting.ICraftingRequester;
import appeng.api.networking.security.IActionSource;
import appeng.api.networking.crafting.ICraftingSubmitResult;
import appeng.api.networking.crafting.*;
import appeng.crafting.execution.CraftingSubmitResult;
import appeng.crafting.execution.ExecutingCraftingJob;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.torchednova.nnindustextras.ItemsStageController;


import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

// ⚠️ Replace this with the ACTUAL class that contains trySubmitJob
//appeng.crafting.execution.CraftingCpuLogic
@Mixin(appeng.crafting.execution.CraftingCpuLogic.class)
class CraftingCpuSubmitMixin {

    @Shadow
    private ExecutingCraftingJob job;

    @Inject(
            method = "trySubmitJob",
            at = @At("HEAD"),
            cancellable = true
    )
    public void nnindustextras$onTrySubmitJob(
            IGrid grid,
            ICraftingPlan plan,
            IActionSource src,
            @Nullable ICraftingRequester requester,
            CallbackInfoReturnable<ICraftingSubmitResult> cir
    ) {

        // Try to get the player from the action source
        src.player().ifPresent(player -> {
            if (!(player instanceof ServerPlayer serverPlayer)) {
                return;
            }

            ResourceLocation itemId = plan.finalOutput().what().getId();

            //LOGGER.info(itemId.toString());

            if (itemId == null) return;

            if (ItemsStageController.unlocked(itemId.toString(), player)) {

                serverPlayer.displayClientMessage(
                        Component.literal(
                                "You have not unlocked autocrafting for " + itemId
                        ),
                        true
                );
                cir.setReturnValue(
                        CraftingSubmitResult.INCOMPLETE_PLAN
                );

            }
        });
    }
}