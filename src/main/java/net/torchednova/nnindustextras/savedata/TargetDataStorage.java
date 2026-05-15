package net.torchednova.nnindustextras.savedata;


import com.google.common.reflect.TypeToken;
import net.minecraft.server.MinecraftServer;
import net.torchednova.nnindustextras.Players.PlayerInfo;
import net.torchednova.nnindustextras.Players.PlayerInfoController;
import net.torchednova.nnindustextras.Players.PromotionController;
import net.torchednova.nnindustextras.Players.Promotions;
import net.torchednova.nnindustextras.freeze.FreezePlayer;
import net.torchednova.nnindustextras.referrals.Gives;
import net.torchednova.nnindustextras.referrals.GivesManager;
import net.torchednova.nnindustextras.referrals.Referral;
import net.torchednova.nnindustextras.referrals.ReferralManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;


public class TargetDataStorage {

    private static final Type LIST_TYPE = new TypeToken<List<Referral>>() {}.getType();
    private static final Type LIST_TYPE_GIVES = new TypeToken<List<Gives>>() {}.getType();
    private static final Type TYPE_String = new TypeToken<String>() {}.getType();
    private static final Type TYPE_LIST_PI = new TypeToken<List<PlayerInfo>>() {}.getType();
    private static final Type TYPE_LIST_PROM = new TypeToken<List<Promotions>>() {}.getType();

    public static void save(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getReferralDataFile(server);

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

    public static ArrayList<Referral> load(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getReferralDataFile(server);

            if (Files.exists(file) == false)
            {
                return new ArrayList<Referral>();
            }

            String json = Files.readString(file);

            ArrayList<Referral> data = ModJson.GSON.fromJson(json, LIST_TYPE);

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

            ArrayList<Gives> data = ModJson.GSON.fromJson(json, LIST_TYPE_GIVES);

            return data != null ? data : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void IRSsave(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getIRSKeyDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String IRSload(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getIRSKeyDataFile(server);

            if (Files.exists(file) == false)
            {
                //LOGGER.info("no file found");
                IRSsave(server);
            }

            String json = Files.readString(file);

            //LOGGER.info(json);

            String data = ModJson.GSON.fromJson(json, TYPE_String);

            return data != null ? data : "";

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void PlayerSave(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getPlayerDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            String json = ModJson.GSON.toJson(PlayerInfoController.Players);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<PlayerInfo> PlayerLoad(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getPlayerDataFile(server);

            if (Files.exists(file) == false)
            {
                //LOGGER.info("no file found");
                PlayerSave(server);
            }

            String json = Files.readString(file);

            //LOGGER.info(json);

            ArrayList<PlayerInfo> data = ModJson.GSON.fromJson(json, TYPE_LIST_PI);

            return data != null ? data : new ArrayList<PlayerInfo>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<PlayerInfo>();
        }
    }

    public static void PromSave(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getPromDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            String json = ModJson.GSON.toJson(PromotionController.promList);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Promotions> PromLoad(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getPromDataFile(server);

            if (Files.exists(file) == false)
            {
                //LOGGER.info("no file found");
                PromSave(server);
            }

            String json = Files.readString(file);

            //LOGGER.info(json);

            ArrayList<Promotions> data = ModJson.GSON.fromJson(json, TYPE_LIST_PROM);

            return data != null ? data : new ArrayList<Promotions>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Promotions>();
        }
    }

}
