package net.torchednova.nnindustextras.referrals;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.torchednova.nnindustextras.savedata.TargetDataStorage;

import java.util.ArrayList;
import java.util.UUID;

public class GivesManager {
    public static ArrayList<Gives> gs;

    public static void init(MinecraftServer server)
    {
        gs = TargetDataStorage.loadGives(server);
    }

    public static void addGive(UUID u)
    {
        gs.add(new Gives(u, gs.size()));
    }


    public static void onPlayerJoin(Entity p)
    {
        if (gs.isEmpty()) return;

        UUID uuid = p.getUUID();

        for (int i = 0; i < gs.size(); i++)
        {
            if (gs.get(i).player.compareTo(uuid) == 0)
            {
                Item I = BuiltInRegistries.ITEM.get(ResourceLocation.parse("gachamachine:gacha_coin"));
                ItemStack is = new ItemStack(I, 5);
                p.getServer().getPlayerList().getPlayer(uuid).getInventory().add(is);

                gs.remove(i);

                TargetDataStorage.saveGives(p.getServer());
                break;
            }
        }
    }


}
