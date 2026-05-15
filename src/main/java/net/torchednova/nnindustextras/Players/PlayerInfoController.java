package net.torchednova.nnindustextras.Players;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.torchednova.nnindustextras.savedata.TargetDataStorage;
import xyz.neonetwork.neolib.utilities.NeoNotify;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerInfoController {
	public static ArrayList<PlayerInfo> Players;

	public static void init(MinecraftServer server)
	{
		//Players = new ArrayList<>();
		Players = TargetDataStorage.PlayerLoad(server);
	}

	private static void add(Player p)
	{
		Players.add(new PlayerInfo(p.getStringUUID(), p.getScoreboardName()));
	}

	public static boolean ownShop(Player p)
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (Objects.equals(Players.get(i).uuid, p.getStringUUID()))
			{
				if (Players.get(i).ownStore != -1) return true;
				else return false;
			}
		}

		return false;
	}


	public static PlayerInfo get(Player p)
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (Objects.equals(Players.get(i).uuid, p.getStringUUID()))
			{
				return Players.get(i);
			}
		}

		add(p);
		return get(p);
	}

	public static void setStore(Player p, int id)
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (Objects.equals(Players.get(i).uuid, p.getStringUUID()))
			{
				Players.get(i).ownStore = id;
				return;
			}
		}
	}

	public static boolean storealreadyowned(int id)
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (Players.get(i).ownStore == id)
			{
				return true;
			}
		}

		return false;
	}


	public static void checkIfStoreTimedOut(Player p)
	{
		if (!p.hasPermissions(2)) return;

		PlayerInfo pi = get(p);

		if (pi.ownStore != -1)
		{
			if (pi.lastSeen +  2592000 <= System.currentTimeMillis())
			{
				NeoNotify.sendChat((ServerPlayer) p, "Player: " + pi.name + " Who Owns Store " + pi.ownStore + " has not been seen for 30 days");
			}
		}

		pi.lastSeen = System.currentTimeMillis();
	}

	public static void checkIfChangedName(Player p)
	{
		PlayerInfo pi = get(p);

		if (Objects.equals(pi.uuid, p.getStringUUID()))
		{
			if (!p.getScoreboardName().equals(pi.name))
			{
				pi.name = p.getScoreboardName();
			}
		}
	}

	public static void playerLoggedOut(Player p)
	{
		PlayerInfo pi = PlayerInfoController.get(p);
		if (pi.timePlayed <= 0) pi.timePlayed = 0;
		pi.timePlayed = pi.timePlayed + (System.currentTimeMillis() - pi.lastSeen);
		pi.lastSeen = System.currentTimeMillis();
	}






}
