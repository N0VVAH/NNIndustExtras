package net.torchednova.nnindustextras;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.simibubi.create.AllItems;
import appeng.items.*;
import appeng.crafting.pattern.*;
import appeng.me.service.CraftingService;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

public class AEStageCheck {

    @SubscribeEvent
    public static void onPatternWrite(PlayerEvent.ItemCraftedEvent event) {
        LOGGER.info("here");
        Player player = event.getEntity();

        ItemStack stack = event.getCrafting();
        if (stack == null || stack.isEmpty()) return;

        Item item = stack.getItem();
        if (item == null) return;


        ResourceLocation id = BuiltInRegistries.ITEM.getKey((item));
        LOGGER.info(id.toString());

        if (item.getClass().getPackageName().equals("AE2:pattern"))
        {

        }
    }
}
