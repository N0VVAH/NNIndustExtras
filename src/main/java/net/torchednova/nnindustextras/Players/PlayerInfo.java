package net.torchednova.nnindustextras.Players;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo {
	public PlayerInfo(String uuid, String name)
	{
		this.name = name;
		this.uuid = uuid;
		lastSeen = System.currentTimeMillis();
		otherStores = new ArrayList<>();
		this.timePlayed = 0;
		this.promoted = 0;
		doPromote = true;
	}

	public String uuid;
	public String name;
	public long lastSeen;
	public long timePlayed;
	public int ownStore = -1;
	public List<Integer> otherStores;
	public int promoted;
	public boolean doPromote;
}
