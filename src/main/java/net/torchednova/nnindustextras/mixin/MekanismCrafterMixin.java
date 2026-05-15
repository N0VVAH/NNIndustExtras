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
                if (((ISecurityTile) this).getOwnerUUID() == null) return;
                LOGGER.info(security.getOwnerName());

                Level level = ((BlockEntity) (Object) this).getLevel();

                if (level.getServer() == null) return;

                MinecraftServer server = level.getServer();

                Player player = getPlayerFromUUID(server, security.getOwnerUUID());

                ResourceLocation id = this.lastOutputStack.getItem().builtInRegistryHolder().key().location();
                LOGGER.info(id.toString() + " | " + player.toString());

                if (ItemsStageController.unlocked(id.toString(), player)) {

                    // STOP Mekanism from calculating the recipe
                    this.lastOutputStack = ItemStack.EMPTY;
                    cir.setReturnValue(false);
                }
            }
        }

    }
}