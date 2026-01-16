package net.torchednova.nnindustextras.referrals;

import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class Gives {
    Gives(UUID player, int id) {
        this.player = player;
        this.id = id;
    }

    public UUID player;
    public int id;
}
