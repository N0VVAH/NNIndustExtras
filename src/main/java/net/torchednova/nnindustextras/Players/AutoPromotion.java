package net.torchednova.nnindustextras.Players;

import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.torchednova.nnindustextras.NNIndustExtras;
import net.torchednova.nnindustextras.savedata.TargetDataStorage;
import xyz.neonetwork.neolib.utilities.NeoNotify;

public class AutoPromotion {
	public static void checkPlayerForPromotion(Player p)
	{
		if(NNIndustExtras.getServer() == null) return;
		if (p.hasPermissions(2)) return;
		PlayerInfo pi = PlayerInfoController.get(p);
		if (pi.doPromote == false) return;

		for (int i = 0; i < PromotionController.promList.size(); i++)
		{
			if (pi.timePlayed >= PromotionController.promList.get(i).time && pi.promoted == PromotionController.promList.get(i).step)
			{
				CommandSourceStack css = NNIndustExtras.getServer().createCommandSourceStack();
				var disp = NNIndustExtras.getServer().getCommands().getDispatcher();
				NNIndustExtras.LOGGER.info("lp user " + p.getScoreboardName() + " promote");
				ParseResults<CommandSourceStack> parse = disp.parse("luckperms user " + p.getScoreboardName() + " promote", css);

				NNIndustExtras.getServer().getCommands().performPrefixedCommand(css, "luckperms user " + p.getScoreboardName() + " promote");
				pi.promoted++;
				NeoNotify.sendTitle((ServerPlayer)p, Component.literal("You have been promoted!"), Component.literal("You now have more Chunk Claims and Loads"));
				//NeoNotify.playSound(SoundEvents.PLAYER_LEVELUP);
				return;
			}
		}


	}
}
