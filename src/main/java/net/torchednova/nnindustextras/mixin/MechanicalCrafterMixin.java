package net.torchednova.nnindustextras.mixin;

import com.google.common.reflect.TypeToken;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import com.simibubi.create.content.kinetics.crafter.RecipeGridHandler;
import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.ftb.mods.ftbchunks.api.FTBChunksAPI;
import dev.ftb.mods.ftblibrary.math.ChunkDimPos;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.torchednova.nnindustextras.ItemsStageController;
import net.torchednova.nnindustextras.NNIndustExtras;
import net.torchednova.nnindustextras.ownertracker;
import net.torchednova.nnindustextras.savedata.ModJson;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.UUID;

import static net.torchednova.nnindustextras.NNIndustExtras.LOGGER;
import static org.spongepowered.asm.util.Bytecode.isVirtual;

@Mixin(MechanicalCrafterBlockEntity.class)
public abstract class mechanicalcraftermixin implements ownertracker {



	@Shadow
	protected RecipeGridHandler.GroupedItems groupedItemsBeforeCraft;
	@Shadow
	protected RecipeGridHandler.GroupedItems groupedItems;

	@Shadow
	public abstract void eject();

	@Shadow
	public abstract void ejectWholeGrid();

	@Shadow
	public abstract MechanicalCrafterBlockEntity.Inventory getInventory();

	@Unique
	private String owner = null;

	@Unique
	private BlockPos pos = null;

	@Unique
	private ResourceKey<Level> levelCheck = null;

	@Override
	public void nnindust$setOwner(String owners)
	{
		this.owner = owners;
		//LOGGER.info(this.owner);
	}

	@Override
	public String nnindust$getOwner() {
		return "";
	}

	@Override
	public void nnindust$setLevel(ResourceKey<Level> levels)
	{
		this.levelCheck = levels;
	}

	@Override
	public Level nnindust$getLevel() {
		return null;
	}

	@Override
	public void nnindust$setPos(BlockPos pos)
	{
		this.pos = pos;
	}

	@Override
	public BlockPos nnindust$getPos() {
		return null;
	}

	@Inject(
		method = "write",
		at = @At("HEAD"),
		cancellable = true
	)
	public void write(
		CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket, CallbackInfo ci
	) {
		if (this.owner != null && !(this.owner.isEmpty())) {
			compound.putString("O", ModJson.GSON.toJson(this.owner));
			//LOGGER.info("Owner writing: " + this.owner);
		}
		if (this.pos != null)
		{
			compound.putString("Pos",  ModJson.GSON.toJson(this.pos));
			//LOGGER.info("POS writing: " + this.pos);
		}
		if (this.levelCheck != null)
		{
			compound.putString("LevelCheck", ModJson.GSON.toJson(this.levelCheck));
			//LOGGER.info("LEVEL writing: " + this.levelCheck);
		}
	}

	@Inject(
		method = "tick",
		at = @At("HEAD"),
		cancellable = true
	)
	public void tick(
		CallbackInfo ci
	) {

		//NNIndustExtras.LOGGER.info("here---------------------------");
		if (owner == null) return;
		if (owner.isEmpty()) return;


		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
		if (server == null) {
			NNIndustExtras.LOGGER.info("server null somehow");
			return;
		}

		if (server.getPlayerList().getPlayer(UUID.fromString(this.owner)) == null) {
			NNIndustExtras.LOGGER.info("player not found");
			return;
		}



		Object cheese;
		try {
			var field = MechanicalCrafterBlockEntity.class.getDeclaredField("phase");
			field.setAccessible(true);
			cheese = field.get(this);
		}catch (NoSuchFieldException | IllegalAccessException e) {
			return;
		}

		if (RecipeGridHandler.getTargetingCrafter((MechanicalCrafterBlockEntity)(Object)this) != null) return;
		if (cheese.toString().equals("ASSEMBLING"))
		{
			groupedItemsBeforeCraft = groupedItems;
		}
		if (!cheese.toString().equals("CRAFTING")) return;


		ChunkPos chunkpos = new ChunkPos(pos);
		ChunkDimPos chunkDimPos = new ChunkDimPos(this.levelCheck, chunkpos);
		ClaimedChunk chunkTeam = FTBChunksAPI.api().getManager().getChunk(chunkDimPos);


		Team playerTeam = FTBTeamsAPI.api().getManager().getTeamForPlayer(server.getPlayerList().getPlayer(UUID.fromString(this.owner))).get();
		if (playerTeam.getMembers() == null) {
			groupedItems = groupedItemsBeforeCraft;
			this.ejectWholeGrid();
			ci.cancel();
			return;
		}
		if (chunkTeam == null)
		{
			groupedItems = groupedItemsBeforeCraft;
			this.ejectWholeGrid();
			ci.cancel();
			return;
		}

		if (!chunkTeam.getTeamData().getTeam().equals(playerTeam) || !(chunkTeam.getTeamData().getTeam().getMembers().contains(UUID.fromString(this.owner))))
		{
			groupedItems = groupedItemsBeforeCraft;
			this.ejectWholeGrid();
			ci.cancel();
			return;
		}

		Pair<Integer, Integer> p = new ImmutablePair<>(0, 0);
		ItemStack is = ((gridmixin)groupedItems).getGrid().get(p);

		//LOGGER.info(is.getItem().builtInRegistryHolder().key().location().toString());

		//LOGGER.info(NNIndustExtras.getServer().getPlayerList().getPlayer(UUID.fromString(this.owner)).toString());

		if (ItemsStageController.unlocked(is.getItem().builtInRegistryHolder().key().location().toString(), server.getPlayerList().getPlayer(UUID.fromString(this.owner))))
		{
			LOGGER.info("not unlocked");
			groupedItems = groupedItemsBeforeCraft;
			this.ejectWholeGrid();
			ci.cancel();
		}
	}

	@Inject(
		method = "read",
		at = @At("HEAD"),
		cancellable = true
	)
	public void read(
		CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket, CallbackInfo ci
	) {
		//if (clientPacket == true) return;
		this.owner = ModJson.GSON.fromJson(compound.getString("O"), new TypeToken<String>() {}.getType());
		//LOGGER.info("Owner reading: " + this.owner);
		this.pos = ModJson.GSON.fromJson(compound.getString("Pos"), new TypeToken<BlockPos>() {}.getType());
		//LOGGER.info("POS reading: " + this.pos);
		this.levelCheck = ModJson.GSON.fromJson(compound.getString("LevelCheck"), new TypeToken<ResourceKey<Level>>() {}.getType());
		//LOGGER.info("LEVEL reading: " + this.levelCheck);
	}
}
