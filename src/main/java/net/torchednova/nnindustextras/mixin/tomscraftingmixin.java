package net.torchednova.nnindustextras.mixin;

import com.tom.storagemod.StorageMod;
import com.tom.storagemod.menu.CraftingTerminalMenu;
import com.tom.storagemod.polymorph.PolymorphHelper;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.torchednova.nnindustextras.ItemsStageController;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Optional;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

@Mixin(targets = "com.tom.storagemod.block.entity.CraftingTerminalBlockEntity")
public class tomscraftingmixin {


    @Shadow
    private WeakReference<Player> polymorphPlayer;

    @Inject(
            method = "getRecipe",
            at = @At("HEAD"),
            cancellable = true
    )
    public void nnindustextras$onTake(CraftingInput input, CallbackInfoReturnable<Optional<RecipeHolder<CraftingRecipe>>> cir) {
        LOGGER.info("here");

        if (StorageMod.polymorph && this.polymorphPlayer != null) {
            Player player = this.polymorphPlayer.get();
            LOGGER.info("here1");
            CraftingRecipe cr =  PolymorphHelper.getRecipe(player, RecipeType.CRAFTING, input, player.level()).get().value();
            LOGGER.info("here3");
            if (!cr.isIncomplete())
            {
                LOGGER.info("here4");
                ResourceLocation id = cr.getResultItem(player.level().registryAccess()).getItem().builtInRegistryHolder().key().location();
                LOGGER.info(id.toString());
            }
        }

    }
}
