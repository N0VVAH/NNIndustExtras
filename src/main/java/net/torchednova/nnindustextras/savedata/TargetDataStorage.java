package net.torchednova.nnindustextras.savedata;


import com.google.common.reflect.TypeToken;
import net.minecraft.server.MinecraftServer;
import net.torchednova.nnindustextras.referrals.Gives;
import net.torchednova.nnindustextras.referrals.GivesManager;
import net.torchednova.nnindustextras.referrals.ReferralManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class TargetDataStorage {

    private static final Type LIST_TYPE = new TypeToken<List<Integer>>() {}.getType();
    private static final Type LIST_TYPE_GIVES = new TypeToken<List<Gives>>() {}.getType();

    public static void save(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getLadderDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            String json = ModJson.GSON.toJson(ReferralManager.rs);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> load(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getLadderDataFile(server);

            if (Files.exists(file) == false)
            {
                return new ArrayList<Integer>();
            }

            String json = Files.readString(file);

            ArrayList<Integer> data = ModJson.GSON.fromJson(json, LIST_TYPE);

            return data != null ? data : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveGives(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getGivesDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            String json = ModJson.GSON.toJson(GivesManager.gs);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Gives> loadGives(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getGivesDataFile(server);

            if (Files.exists(file) == false)
            {
                return new ArrayList<Gives>();
            }

            String json = Files.readString(file);

            ArrayList<Gives> data = ModJson.GSON.fromJson(json, LIST_TYPE);

            return data != null ? data : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
