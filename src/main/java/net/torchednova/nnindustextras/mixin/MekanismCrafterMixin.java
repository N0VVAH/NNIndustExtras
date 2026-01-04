package net.torchednova.nnindustextras.mixin;


import mekanism.common.lib.security.ISecurityTile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.torchednova.nnindustextras.ItemsStageController;


import static com.alessandro.astages.util.AStagesUtil.getPlayerFromUUID;
import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

@Mixin(targets = "mekanism.common.tile.machine.TileEntityFormulaicAssemblicator")
public abstract class MekanismCrafterMixin {



    @Shadow
    private ItemStack lastOutputStack;


    @Inject(
            method = "craftSingle",
            at = @At("HEAD"),
            cancellable = true
    )
    public void nnindustextras$craftSingle(CallbackInfoReturnable<Boolean> cir) {

        LOGGER.info("Here");
        if (!this.lastOutputStack.isEmpty())
        {
            if(this instanceof ISecurityTile security) {
                LOGGER.info("Here1");
                if (((ISecurityTile) this).getOwnerUUID() == null) return;
                LOGGER.info("Here2");
                LOGGER.info(security.getOwnerName());

                Level level = ((BlockEntity) (Object) this).getLevel();
                LOGGER.info("Here3");

                if (level.getServer() == null) return;
                LOGGER.info("Here4");

                MinecraftServer server = level.getServer();
                LOGGER.info("Here5");

                Player player = getPlayerFromUUID(server, security.getOwnerUUID());
                LOGGER.info("Here6");

                ResourceLocation id = this.lastOutputStack.getItem().builtInRegistryHolder().key().location();
                LOGGER.info("Here7");
                LOGGER.info(id.toString() + " | " + player.toString());

                if (ItemsStageController.unlocked(id.toString(), player)) {
                    LOGGER.info("Here8");

                    // STOP Mekanism from calculating the recipe
                    this.lastOutputStack = ItemStack.EMPTY;
                    cir.setReturnValue(false);
                }
            }
        }

    }
}