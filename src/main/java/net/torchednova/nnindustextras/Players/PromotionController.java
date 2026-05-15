package net.torchednova.nnindustextras.Players;

import net.minecraft.server.MinecraftServer;
import net.torchednova.nnindustextras.savedata.TargetDataStorage;

import java.util.ArrayList;

public class PromotionController {
	public static ArrayList<Promotions> promList;

	public static void init(MinecraftServer server)
	{
		promList = new ArrayList<>();
		promList = TargetDataStorage.PromLoad(server);
	}

	public static void close(MinecraftServer server)
	{
		//promList.add(new Promotions(3456000, 0));
		//promList.add(new Promotions(8640000, 1));
		TargetDataStorage.PromSave(server);
	}


}
