package net.torchednova.nnindustextras.savedata;

import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;

public class ModDataPath {
    public static Path getReferralDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("referrals.json");
    }

    public static Path getGivesDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("gives.json");
    }

    public static Path getIRSKeyDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("irs.json");
    }

    public static Path getPlayerDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("playerinfo.json");
    }

    public static Path getPromDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("nnindustextras").resolve("promotion.json");
    }

}