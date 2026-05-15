package net.torchednova.nnindustextras;

import com.alessandro.astages.capability.PlayerStage;
import com.alessandro.astages.util.AStagesUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;

public class ItemsStageController {
    static Map<String, ArrayList<String>> ItemStages = new HashMap<String, ArrayList<String>>();

    static void init()
    {
        ItemStages.put("mek_basic", GetArrayList("mekanism:metallurgic_infuser", "mekanism:basic_energy_cube", "mekanism:enrichment_chamber", "mekanism:crusher", "mekanism:precision_sawmill", "evolvedmekanism:alloyer", "mekanismgenerators:advanced_solar_generator", "mekanism:electrolytic_separator"));
        ItemStages.put("mek_jetpack", GetArrayList("mekanism:free_runners", "mekanism:jetpack"));
        ItemStages.put("mek_scuba", GetArrayList("mekanism:scuba_mask", "mekanism:scuba_tank"));
        ItemStages.put("mek_advanced", GetArrayList("mekanism:advanced_universal_cable", "mekanism:advanced_pressurized_tube", "mekanism:advanced_mechanical_pipe", "mekanism:advanced_logistical_transporter", "mekanism:advanced_thermodynamic_conductor", "mekanism:advanced_energy_cube", "mekanism:osmium_compressor", "mekanism:purification_chamber", "mekanism:basic_smelting_factory", "mekanism:basic_enriching_factory", "mekanism:basic_crushing_factory", "mekanism:basic_compressing_factory", "mekanism:basic_combining_factory", "mekanism:basic_purifying_factory", "mekanism:basic_injecting_factory", "mekanism:basic_infusing_factory", "mekanism:basic_sawing_factory", "evolvedmekanism:basic_alloying_factory", "evolvedmekanism:advanced_solar_generator", "evolvedmekanism:advanced_personal_barrel", "evolvedmekanism:advanced_personal_chest", "mekanism:advanced_chemical_tank", "mekanism:basic_induction_cell", "mekanism:basic_induction_provider", "mekanism:advanced_fluid_tank", "mekanism:advanced_bin", "evolvedmekanism:solidification_chamber", "evolvedmekanism:thermalizer", "mekanism:basic_tier_installer", "mekanism:formulaic_assemblicator", "mekanism:painting_machine", "mekanism:pressurized_reaction_chamber", "mekanism:chemical_infuser", "mekanism:chemical_oxidizer", "mekanism:rotary_condensentrator", "mekanism:advanced_control_circuit"));
        ItemStages.put("mek_speed", GetArrayList("mekanism:upgrade_speed", "mekanism:upgrade_filter", "mekanism:upgrade_muffling", "mekanism:upgrade_energy", "mekanism:upgrade_chemical", "mekanism:upgrade_stone_generator"));
        ItemStages.put("mek_weapon", GetArrayList("mekanism:electric_bow", "mekanism:flamethrower"));
        ItemStages.put("mek_elite", GetArrayList("mekanism:elite_universal_cable", "mekanism:elite_pressurized_tube", "mekanism:elite_mechanical_pipe", "mekanism:elite_logistical_transporter", "mekanism:elite_thermodynamic_conductor", "mekanism:elite_energy_cube", "mekanism:combiner", "mekanism:chemical_injection_chamber", "mekanism:advanced_smelting_factory", "mekanism:advanced_enriching_factory", "mekanism:advanced_crushing_factory", "mekanism:advanced_compressing_factory", "mekanism:advanced_combining_factory", "mekanism:advanced_purifying_factory", "mekanism:advanced_injecting_factory", "mekanism:advanced_infusing_factory", "mekanism:advanced_sawing_factory", "evolvedmekanism:advanced_alloying_factory", "evolvedmekanism:elite_solar_generator", "evolvedmekanism:elite_personal_barrel", "evolvedmekanism:elite_personal_chest", "mekanism:elite_chemical_tank", "mekanism:advanced_induction_cell", "mekanism:advanced_induction_provider", "mekanism:elite_fluid_tank", "mekanism:elite_bin", "mekanism:advanced_tier_installer", "mekanism:pigment_mixer", "mekanism:solar_neutron_activator", "mekanism:elite_control_circuit", "mekanism:logistical_sorter"));

        ItemStages.put("digital_miner", GetArrayList("mekanism:digital_miner"));

        ItemStages.put("mek_ultimate", GetArrayList("mekanism:ultimate_universal_cable", "mekanism:ultimate_pressurized_tube", "mekanism:ultimate_mechanical_pipe", "mekanism:ultimate_logistical_transporter", "mekanism:ultimate_thermodynamic_conductor", "mekanism:ultimate_energy_cube", "mekanism:elite_smelting_factory", "mekanism:elite_enriching_factory", "mekanism:elite_crushing_factory", "mekanism:elite_compressing_factory", "mekanism:elite_combining_factory", "mekanism:elite_purifying_factory", "mekanism:elite_injecting_factory", "mekanism:elite_infusing_factory", "mekanism:elite_sawing_factory", "evolvedmekanism:elite_alloying_factory", "evolvedmekanism:ultimate_solar_generator", "evolvedmekanism:ultimate_personal_barrel", "evolvedmekanism:ultimate_personal_chest", "mekanism:ultimate_chemical_tank", "mekanism:elite_induction_cell", "mekanism:elite_induction_provider", "mekanism:ultimate_fluid_tank", "mekanism:ultimate_bin", "mekanism:ultimate_control_circuit", "mekanism:elite_tier_installer", "mekanism:antiprotonic_nucleosynthesizer", "mekanism:isotopic_centrifuge", "mekanism:chemical_crystallizer", "mekanism:chemical_washer", "mekanism:chemical_dissolution_chamber", "mekanism:hdpe_elytra", "mekanism:boiler_casing", "mekanism:quantum_entangloporter"));

        ItemStages.put("mek_mek", GetArrayList("mekanism:meka_tool", "mekanism:mekasuit_boots", "mekanism:mekasuit_pants", "mekanism:mekasuit_bodyarmor", "mekanism:mekasuit_helmet"));

        ItemStages.put("mek_overclocked", GetArrayList("evolvedmekanism:overclocked_universal_cable", "evolvedmekanism:overclocked_pressurized_tube", "evolvedmekanism:overclocked_mechanical_pipe", "evolvedmekanism:overclocked_logistical_transporter", "evolvedmekanism:overclocked_thermodynamic_conductor", "evolvedmekanism:overclocked_energy_cube", "mekanism:ultimate_smelting_factory", "mekanism:ultimate_enriching_factory", "mekanism:ultimate_crushing_factory", "mekanism:ultimate_compressing_factory", "mekanism:ultimate_combining_factory", "mekanism:ultimate_purifying_factory", "mekanism:ultimate_injecting_factory", "mekanism:ultimate_infusing_factory", "mekanism:ultimate_sawing_factory", "evolvedmekanism:ultimate_alloying_factory", "evolvedmekanism:overclocked_solar_generator", "evolvedmekanism:overclocked_personal_barrel", "evolvedmekanism:overclocked_personal_chest", "evolvedmekanism:overclocked_chemical_tank", "mekanism:ultimate_induction_cell", "mekanism:ultimate_induction_provider", "evolvedmekanism:overclocked_fluid_tank", "evolvedmekanism:overclocked_bin", "evolvedmekanism:overclocked_control_circuit", "mekanism:ultimate_tier_installer", "evolvedmekanism:upgrade_radioactive", "evolvedmekanism:upgrade_solar"));
        ItemStages.put("mek_quantum", GetArrayList("evolvedmekanism:quantum_universal_cable", "evolvedmekanism:quantum_pressurized_tube", "evolvedmekanism:quantum_mechanical_pipe", "evolvedmekanism:quantum_logistical_transporter", "evolvedmekanism:quantum_thermodynamic_conductor", "evolvedmekanism:quantum_energy_cube", "evolvedmekanism:overclocked_smelting_factory", "evolvedmekanism:overclocked_enriching_factory", "evolvedmekanism:overclocked_crushing_factory", "evolvedmekanism:overclocked_compressing_factory", "evolvedmekanism:overclocked_combining_factory", "evolvedmekanism:overclocked_purifying_factory", "evolvedmekanism:overclocked_injecting_factory", "evolvedmekanism:overclocked_infusing_factory", "evolvedmekanism:overclocked_sawing_factory", "evolvedmekanism:overclocked_alloying_factory", "evolvedmekanism:quantum_solar_generator", "evolvedmekanism:quantum_personal_barrel", "evolvedmekanism:quantum_personal_chest", "evolvedmekanism:quantum_chemical_tank", "evolvedmekanism:overclocked_induction_cell", "evolvedmekanism:overclocked_induction_provider", "evolvedmekanism:quantum_fluid_tank", "evolvedmekanism:quantum_bin", "evolvedmekanism:quantum_control_circuit", "evolvedmekanism:overclocked_tier_installer"));
        ItemStages.put("mek_dense", GetArrayList("evolvedmekanism:dense_universal_cable", "evolvedmekanism:dense_pressurized_tube", "evolvedmekanism:dense_mechanical_pipe", "evolvedmekanism:dense_logistical_transporter", "evolvedmekanism:dense_thermodynamic_conductor", "evolvedmekanism:dense_energy_cube", "evolvedmekanism:quantum_smelting_factory", "evolvedmekanism:quantum_enriching_factory", "evolvedmekanism:quantum_crushing_factory", "evolvedmekanism:quantum_compressing_factory", "evolvedmekanism:quantum_combining_factory", "evolvedmekanism:quantum_purifying_factory", "evolvedmekanism:quantum_injecting_factory", "evolvedmekanism:quantum_infusing_factory", "evolvedmekanism:quantum_sawing_factory", "evolvedmekanism:quantum_alloying_factory", "evolvedmekanism:dense_solar_generator", "evolvedmekanism:dense_personal_barrel", "evolvedmekanism:dense_personal_chest", "evolvedmekanism:dense_chemical_tank", "evolvedmekanism:quantum_induction_cell", "evolvedmekanism:quantum_induction_provider", "evolvedmekanism:dense_fluid_tank", "evolvedmekanism:dense_bin", "evolvedmekanism:dense_control_circuit", "evolvedmekanism:chemixer", "evolvedmekanism:quantum_tier_installer"));
        ItemStages.put("mek_multiverse", GetArrayList("evolvedmekanism:multiversal_universal_cable", "evolvedmekanism:multiversal_pressurized_tube", "evolvedmekanism:multiversal_mechanical_pipe", "evolvedmekanism:multiversal_logistical_transporter", "evolvedmekanism:multiversal_thermodynamic_conductor", "evolvedmekanism:multiversal_energy_cube", "evolvedmekanism:dense_smelting_factory", "evolvedmekanism:dense_enriching_factory", "evolvedmekanism:dense_crushing_factory", "evolvedmekanism:dense_compressing_factory", "evolvedmekanism:dense_combining_factory", "evolvedmekanism:dense_purifying_factory", "evolvedmekanism:dense_injecting_factory", "evolvedmekanism:dense_infusing_factory", "evolvedmekanism:dense_sawing_factory", "evolvedmekanism:dense_alloying_factory", "evolvedmekanism:multiversal_solar_generator", "evolvedmekanism:multiversal_personal_barrel", "evolvedmekanism:multiversal_personal_chest", "evolvedmekanism:multiversal_chemical_tank", "evolvedmekanism:dense_induction_cell", "evolvedmekanism:dense_induction_provider", "evolvedmekanism:multiversal_fluid_tank", "evolvedmekanism:multiversal_bin", "evolvedmekanism:multiversal_control_circuit", "evolvedmekanism:dense_tier_installer"));
        ItemStages.put("mek_nearcreate", GetArrayList("evolvedmekanism:multiversal_smelting_factory", "evolvedmekanism:multiversal_enriching_factory", "evolvedmekanism:multiversal_crushing_factory", "evolvedmekanism:multiversal_compressing_factory", "evolvedmekanism:multiversal_combining_factory", "evolvedmekanism:multiversal_purifying_factory", "evolvedmekanism:multiversal_injecting_factory", "evolvedmekanism:multiversal_infusing_factory", "evolvedmekanism:multiversal_sawing_factory", "evolvedmekanism:multiversal_alloying_factory", "evolvedmekanism:multiversal_induction_cell", "evolvedmekanism:multiversal_induction_provider", "evolvedmekanism:multiversal_tier_installer"));
        ItemStages.put("mek_create", GetArrayList("evolvedmekanism:creative_universal_cable", "evolvedmekanism:creative_pressurized_tube", "evolvedmekanism:creative_mechanical_pipe", "evolvedmekanism:creative_logistical_transporter", "evolvedmekanism:creative_thermodynamic_conductor", "mekanism:creative_energy_cube", "evolvedmekanism:creative_smelting_factory", "evolvedmekanism:creative_enriching_factory", "evolvedmekanism:creative_crushing_factory", "evolvedmekanism:creative_compressing_factory", "evolvedmekanism:creative_combining_factory", "evolvedmekanism:creative_purifying_factory", "evolvedmekanism:creative_injecting_factory", "evolvedmekanism:creative_infusing_factory", "evolvedmekanism:creative_sawing_factory", "evolvedmekanism:creative_alloying_factory", "evolvedmekanism:creative_solar_generator", "evolvedmekanism:creative_personal_barrel", "evolvedmekanism:creative_personal_chest", "evolvedmekanism:creative_induction_cell", "evolvedmekanism:creative_induction_provider", "mekanism:creative_fluid_tank", "mekanism:creative_bin", "evolvedmekanism:creative_control_circuit", "evolvedmekanism:creative_tier_installer"));

        ItemStages.put("nuclear_mek_fis", GetArrayList("mekanismgenerators:reactor_glass", "mekanismgenerators:fission_reactor_casing", "mekanismgenerators:fission_reactor_port", "mekanismgenerators:fission_reactor_logic_adapter", "mekanismgenerators:fission_fuel_assembly", "mekanismgenerators:control_rod_assembly", "mekanism:pressure_disperser"));
        ItemStages.put("nuclear_mek_prot", GetArrayList("mekanism:hazmat_mask", "mekanism:hazmat_pants", "mekanism:hazmat_gown", "mekanism:hazmat_boots"));
        ItemStages.put("nuclear_mek_fus", GetArrayList("mekanism:structural_glass", "mekanismgenerators:turbine_valve", "mekanismgenerators:turbine_blade", "mekanismgenerators:turbine_rotor", "mekanismgenerators:rotational_complex", "mekanismgenerators:electromagnetic_coil", "mekanismgenerators:turbine_casing", "mekanismgenerators:turbine_vent", "mekanismgenerators:saturating_condenser", "bfr:fusion_reactor_controller", "bfr:fusion_reactor_port", "bfr:fusion_reactor_frame", "bfr:fusion_reactor_logic_adapter", "bfr:laser_focus_matrix", "bfr:irradiator"));

        ItemStages.put("qio_base", GetArrayList("mekanism:qio_drive_base", "mekanism:qio_drive_array", "mekanism:qio_drive_hyper_dense", "mekanism:qio_dashboard"));
        ItemStages.put("qio_wireless", GetArrayList("mekanism:qio_exporter", "mekanism:qio_importer", "mekanism:qio_redstone_adapter", "mekanism:portable_qio_dashboard"));
        ItemStages.put("qio_mid_drive", GetArrayList("mekanism:qio_drive_time_dilating", "mekanism:qio_drive_supermassive"));
        ItemStages.put("qio_high_drive", GetArrayList("evolvedmekanism:qio_drive_boosted", "evolvedmekanism:qio_drive_singularity"));
        ItemStages.put("qio_realhigh_drive", GetArrayList("evolvedmekanism:qio_drive_hypra_solidified", "evolvedmekanism:qio_drive_black_hole"));

        ItemStages.put("cobblemon", GetArrayList("cobblemon:pc", "cobblemon:healing_machine", "cobblemon:monitor", "cobblemon:fossil_analyzer", "cobblemon:restoration_tank", "cobblemon:pasture"));
        ItemStages.put("cobbleballsadv", GetArrayList("kubejs:prepared_red_apricorn", "kubejs:prepared_yellow_apricorn", "kubejs:prepared_green_apricorn", "kubejs:prepared_blue_apricorn", "kubejs:prepared_pink_apricorn", "kubejs:prepared_black_apricorn", "kubejs:prepared_white_apricorn", "kubejs:great_core", "kubejs:ultra_core"));
        ItemStages.put("cobbleballsbasic", GetArrayList("kubejs:prepared_tumblestone", "kubejs:prepared_black_tumblestone", "kubejs:prepared_sky_tumblestone"));

        ItemStages.put("create", GetArrayList("kubejs:precision_base"));

        ItemStages.put("tfmg", GetArrayList("tfmg:empty_circuit_board", "tfmg:turbine_engine", "tfmg:radial_engine", "tfmg:regular_engine", "kubejs:steel_base"));

        ItemStages.put("powah_low", GetArrayList("powah:thermo_generator_starter", "powah:thermo_generator_basic", "powah:energizing_rod_starter", "powah:energizing_rod_basic", "powah:magmator_starter", "powah:furnator_basic", "powah:energy_cable_basic", "powah:energy_cable_basic", "powah:energy_cell_starter", "powah:energy_cell_basic", "powah:energy_cell_basic", "powah:energy_discharger_starter", "powah:energy_discharger_basic", "powah:energy_hopper_starter", "powah:energy_hopper_basic", "powah:battery_starter", "powah:battery_basic"));
        ItemStages.put("powah_mid", GetArrayList("powah:capacitor_hardened", "powah:thermo_generator_hardened", "powah:thermo_generator_blazing", "powah:energizing_rod_hardened", "powah:magmator_hardened", "powah:solar_panel_hardened", "powah:furnator_hardened", "powah:energy_cable_hardened", "powah:energy_cable_hardened", "powah:energy_cell_hardened", "powah:energy_discharger_hardened", "powah:energy_hopper_hardened", "powah:battery_hardened", "powah:capacitor_blazing"));
        ItemStages.put("powah_high", GetArrayList("powah:thermo_generator_niotic", "powah:reactor_starter", "powah:reactor_basic", "powah:energizing_rod_blazing", "powah:magmator_blazing", "powah:solar_panel_blazing", "powah:furnator_blazing", "powah:energy_cable_blazing", "powah:energy_cable_blazing", "powah:energy_cell_blazing", "powah:energy_discharger_blazing", "powah:energy_hopper_blazing", "powah:battery_blazing", "powah:capacitor_niotic"));
        ItemStages.put("powah_realhigh", GetArrayList("powah:reactor_hardened", "powah:reactor_blazing", "powah:energizing_rod_niotic", "powah:magmator_niotic", "powah:solar_panel_niotic", "powah:furnator_niotic", "powah:energy_cable_niotic", "powah:energy_cable_niotic", "powah:energy_cell_niotic", "powah:energy_discharger_niotic", "powah:energy_hopper_niotic", "powah:thermo_generator_spirited", "powah:battery_niotic", "powah:capacitor_spirited"));
        ItemStages.put("powah_ultra", GetArrayList("powah:thermo_generator_nitro", "powah:reactor_niotic", "powah:reactor_spirited", "powah:energizing_rod_spirited", "powah:magmator_spirited", "powah:solar_panel_spirited", "powah:furnator_spirited", "powah:energy_cable_spirited", "powah:energy_cable_spirited", "powah:energy_cell_spirited", "powah:energy_discharger_spirited", "powah:energy_hopper_spirited", "powah:battery_spirited", "powah:capacitor_nitro", "powah:ender_cell_spirited"));
        ItemStages.put("powah_final", GetArrayList("powah:reactor_nitro", "powah:energizing_rod_nitro", "powah:magmator_nitro", "powah:solar_panel_nitro", "powah:furnator_nitro", "powah:energy_cable_nitro", "powah:energy_cable_nitro", "powah:energy_cell_nitro", "powah:energy_discharger_nitro", "powah:energy_hopper_nitro", "powah:battery_nitro", "powah:ender_cell_nitro"));

        ItemStages.put("powah_wireless_low", GetArrayList("powah:player_transmitter_starter", "powah:player_transmitter_basic", "powah:player_transmitter_hardened", "powah:ender_gate_starter", "powah:ender_gate_basic", "powah:ender_gate_hardened", "powah:ender_cell_starter", "powah:ender_cell_basic", "powah:ender_cell_hardened"));
        ItemStages.put("powah_wireless_mid", GetArrayList("powah:player_transmitter_blazing", "powah:player_transmitter_niotic", "powah:ender_gate_blazing", "powah:ender_gate_niotic", "powah:ender_cell_blazing", "powah:ender_cell_niotic"));
        ItemStages.put("powah_wireless_high", GetArrayList("powah:player_transmitter_spirited", "powah:player_transmitter_nitro", "powah:ender_gate_spirited", "powah:ender_gate_nitro"));

        ItemStages.put("turbine_advanced", GetArrayList("create_new_age:fluxuated_magnetite", "create_new_age:netherite_magnet", "create_new_age:advanced_solar_heating_plate"));
        ItemStages.put("turbine_basic", GetArrayList("create_new_age:carbon_brushes", "create_new_age:generator_coil", "create_new_age:basic_solar_heating_plate"));
        ItemStages.put("nuclear_create", GetArrayList("kubejs:primed_thorium", "create_new_age:reactor_heat_vent", "create_new_age:reactor_fuel_acceptor", "create_new_age:reactor_glass", "crowns:heat_exchanger", "crowns:steam_input", "crowns:steam_collector", "crowns:compressor", "crowns:fuel_assembly"));

        ItemStages.put("aestart", GetArrayList("ae2:inscriber", "ae2:fluix_glass_cable", "ae2:cell_component_1k", "ae2:cell_component_4k", "ae2:item_storage_cell_1k", "ae2:item_storage_cell_4k", "ae2:item_storage_cell_1k", "ae2:item_storage_cell_4k", "ae2:drive", "ae2:terminal", "ae2:crafting_terminal"));
        ItemStages.put("aemid", GetArrayList("rftoolsbase:machine_frame", "ae2:cell_component_16k", "ae2:cell_component_64k", "ae2:item_storage_cell_16k", "ae2:item_storage_cell_64k", "ae2:item_storage_cell_16k", "ae2:item_storage_cell_64k", "ae2:controller", "ae2:interface", "ae2:export_bus", "ae2:import_bus"));
        ItemStages.put("aeend", GetArrayList("ae2:cell_component_256k", "ae2:item_storage_cell_256k", "ae2:item_storage_cell_256k", "ae2:fluix_smart_dense_cable", "ae2:fluix_covered_dense_cable"));

        ItemStages.put("aecraft", GetArrayList("ae2:crafting_unit", "ae2:1k_crafting_storage", "ae2:crafting_monitor", "ae2:pattern_provider"));
        ItemStages.put("aecraftupgrade", GetArrayList("ae2:4k_crafting_storage"));
        ItemStages.put("aecraftmid", GetArrayList("ae2:16k_crafting_storage", "ae2:64k_crafting_storage"));
        ItemStages.put("aecraftend", GetArrayList("ae2:256k_crafting_storage", "ae2:crafting_accelerator"));

        ItemStages.put("extraebase", GetArrayList("extendedae:drive_upgrade", "extendedae:pattern_provider_upgrade", "extendedae:interface_upgrade", "extendedae:io_bus_upgrade", "extendedae:io_bus_upgrade", "extendedae:pattern_terminal_upgrade", "extendedae:ingredient_buffer"));
        ItemStages.put("extraeend", GetArrayList("extendedae:crystal_assembler", "extendedae:assembler_matrix_frame", "extendedae:assembler_matrix_wall", "extendedae:assembler_matrix_glass", "extendedae:machine_frame"));

        ItemStages.put("replication", GetArrayList("replication:matter_network_pipe", "replication:replicator", "replication:identification_chamber", "replication:disintegrator", "replication:replication_terminal", "replication:chip_storage", "replication:matter_tank", "rep_ae2_bridge:rep_ae2_bridge", "rep_ae2_bridge:rep_ae2_bridge"));

        ItemStages.put("tacz_base", GetArrayList("tacz:gun_smith_table", "tacz:workbench_a", "kubejs:bullet_base"));
        ItemStages.put("tacz_attach", GetArrayList("tacz:workbench_c"));

        ItemStages.put("tempad_basic", GetArrayList("tempad:knowledge_projector", "tempad:card_wallet", "tempad:chronon_cell", "tempad:chronon_generator", "tempad:timedoor_projector", "tempad:timedoor_marker"));
        ItemStages.put("tempad_mid", GetArrayList("tempad:chronometer", "tempad:chronon_battery", "tempad:chronomark", "tempad:location_broadcaster"));
        ItemStages.put("tempad_high", GetArrayList("tempad:metronome", "tempad:time_twister", "tempad:screening_device"));
        ItemStages.put("tempad_final", GetArrayList("tempad:tempad", "tempad:workstation", "tempad:player_teleport_upgrade", "tempad:knowledge_repository_upgrade"));

        ItemStages.put("hostile", GetArrayList("hostilenetworks:sim_chamber", "hostilenetworks:loot_fabricator", "hostilenetworks:deep_learner", "hostilenetworks:blank_data_model", "hostilenetworks:prediction_matrix"));

        ItemStages.put("playershells", GetArrayList("playershells:centrifuge", "playershells:shell_forge"));

        ItemStages.put("tss_base", GetArrayList("toms_storage:inventory_connector", "toms_storage:storage_terminal", "toms_storage:crafting_terminal", "toms_storage:open_crate", "toms_storage:trim", "toms_storage:inventory_cable", "toms_storage:inventory_interface", "toms_storage:inventory_cable_connector", "toms_storage:inventory_configurator"));
        ItemStages.put("tss_advanced", GetArrayList("toms_storage:level_emitter", "toms_storage:basic_inventory_hopper", "toms_storage:filing_cabinet", "toms_storage:inventory_proxy", "toms_storage:wireless_terminal", "toms_storage:adv_wireless_terminal"));

        ItemStages.put("buildgadg", GetArrayList("buildinggadgets2:gadget_destruction", "buildinggadgets2:gadget_cut_paste", "buildinggadgets2:gadget_copy_paste", "buildinggadgets2:gadget_exchanging", "buildinggadgets2:gadget_building", "buildinggadgets2:template_manager"));

        ItemStages.put("cm_tiny", GetArrayList("compactmachines:personal_shrinking_device", "kubejs:machinecore_tiny", "compactmachines:wall", "compactmachines:shrinking_module", "compactmachines:enlarging_module"));
        ItemStages.put("cm_small", GetArrayList("kubejs:machinecore_small"));
        ItemStages.put("cm_normal", GetArrayList("kubejs:machinecore_normal"));
        ItemStages.put("cm_large", GetArrayList("kubejs:machinecore_large"));
        ItemStages.put("cm_giant", GetArrayList("kubejs:machinecore_giant"));
        ItemStages.put("cm_colossal", GetArrayList("kubejs:machinecore_colossal"));
        ItemStages.put("cm_supreme", GetArrayList("kubejs:machinecore_supreme"));

        ItemStages.put("flight", GetArrayList("flightring:flight_ring"));

    }

    static ArrayList<String> GetArrayList(String... args)
    {
        ArrayList<String> TempList = new ArrayList<String>();
        Collections.addAll(TempList, args);
        return TempList;
    }

    public static Boolean unlocked(String item, Player ent)
    {
        //PlayerStage ps = new PlayerStage(ent);

        String stage = "";
        //NNIndustExtras.LOGGER.info(item);

        for (String key : ItemStages.keySet())
        {
            //NNIndustExtras.LOGGER.info(key);
            if (ItemStages.get(key).contains(item))
            {
                stage = key;
                NNIndustExtras.LOGGER.info(stage);
                break;
            }
        }

        /*if (ItemStages.get("mek_basic").contains(item)) {
            stage = "mek_basic";
        } else if (ItemStages.get("mek_advanced").contains(item)) {
            stage = "mek_advanced";
        }*/


        if (Objects.equals(stage, "")) return false;

        if (AStagesUtil.hasStage(ent, stage))
        {
            return false;
        }

        return true;
    }
}
