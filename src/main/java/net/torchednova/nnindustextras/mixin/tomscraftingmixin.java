package net.torchednova.nnindustextras.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Optional;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

@Mixin(targets = "com.tom.storagemod.block.entity.CraftingTerminalBlockEntity")
public abstract class tomscraftingmixin {


    @Unique
    private Player nNIndustExtras$temp;

    @Final
    @Shadow
    private CraftingContainer craftMatrix;

    @Shadow
    private Optional<RecipeHolder<CraftingRecipe>> currentRecipe;


    @Inject(
            method = "createMenu",
            at = @At("HEAD")
    )
    public void nnindustextras$createMenu(int id, Inventory plInv, Player arg2, CallbackInfoReturnable<AbstractContainerMenu> cir) {
        nNIndustExtras$temp = arg2;
    }

    @Inject(
            method = "onCraftingMatrixChanged",
            at = @At("HEAD"),
            cancellable = true
    )
    public void nnindustextras$onCraftingMatrixChanged(CallbackInfo ci) {
        LOGGER.info("onCraftingMatrixChanged here1");

        if (nNIndustExtras$temp == null)
        {
            ci.cancel();
        }
        LOGGER.info(nNIndustExtras$temp.toString());

        LOGGER.info("onCraftingMatrixChanged here2");
        CraftingInput input = craftMatrix.asCraftInput();

        LOGGER.info("onCraftingMatrixChanged here3");
        if (currentRecipe.isEmpty()) {
            return;
        }


        LOGGER.info("onCraftingMatrixChanged here4");
        Item item = currentRecipe.get().value().assemble(input, nNIndustExtras$temp.level().registryAccess()).getItem();
        ResourceLocation id = item.builtInRegistryHolder().key().location();

        LOGGER.info("ID: {}", id);
    }
}
