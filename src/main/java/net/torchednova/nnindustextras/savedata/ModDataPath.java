package net.torchednova.nnindustextras.savedata;

import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;

public class ModDataPath {
    public static Path getLadderDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("referrals.json");
    }

    public static Path getGivesDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("gives.json");
    }

}