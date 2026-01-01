package net.torchednova.nnindustextras;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import appeng.items.*;
import appeng.crafting.pattern.*;


import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

public class AEStageCheck {

    @SubscribeEvent
    public static void onPatternWrite(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();

        //LOGGER.info(player.getName().toString());

        ItemStack stack = event.getCrafting();
        if (stack == null || stack.isEmpty()) return;

        Item item = stack.getItem();
        if (item == null) return;


        ResourceLocation id = BuiltInRegistries.ITEM.getKey((item));
        //LOGGER.info(id.toString());

        if (item.getClass().getPackageName().equals("AE2:pattern"))
        {

        }
    }
}
