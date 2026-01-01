package net.torchednova.nnindustextras;

import com.alessandro.astages.capability.PlayerStage;
import com.alessandro.astages.util.AStagesUtil;
import net.minecraft.world.entity.player.Player;

import java.util.*;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

public class ItemsStageController {
    static Map<String, ArrayList<String>> ItemStages = new HashMap<String, ArrayList<String>>();

    static void init()
    {
        ItemStages.put("mek_basic", GetArrayList("mekanism:basic_universal_cable", "mekanism:basic_pressurized_tube", "mekanism:basic_mechanical_pipe", "mekanism:basic_logistical_transporter", "mekanism:basic_thermodynamic_conductor", "mekanism:metallurgic_infuser", "mekanism:basic_energy_cube", "mekanism:energized_smelter", "mekanism:enrichment_chamber", "mekanism:crusher", "mekanism:precision_sawmill"));
        ItemStages.put("mek_advanced", GetArrayList("mekanism:advanced_universal_cable", "mekanism:advanced_pressurized_tube", "mekanism:advanced_mechanical_pipe", "mekanism:advanced_logistical_transporter", "mekanism:advanced_thermodynamic_conductor", "mekanism:advanced_energy_cube", "mekanism:osmium_compressor", "mekanism:alloyer", "mekanism:purification_chamber"));
    }

    static ArrayList<String> GetArrayList(String... args)
    {
        ArrayList<String> TempList = new ArrayList<String>();
        Collections.addAll(TempList, args);
        return TempList;
    }

    public static Boolean unlocked(String item, Player ent)
    {
        PlayerStage ps = new PlayerStage(ent);
        String stage = "";

        if (ItemStages.get("mek_basic").contains(item)) {
            stage = "mek_basic";
        } else if (ItemStages.get("mek_advanced").contains(item)) {
            stage = "mek_advanced";
        }

        if (stage == "") return false;
        if (AStagesUtil.hasStage(ent, stage))
        {
            return false;
        }

        return true;
    }



}
