package net.torchednova.nnindustextras.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;
import net.torchednova.nnindustextras.Players.PlayerInfoController;

public class NeoAutoPromotion {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
			Commands.literal("neoautopromote").requires(source -> source.hasPermission(2))
				.then(Commands.literal("stop")
					.then(Commands.argument("player", EntityArgument.player())
					.executes(context ->
						{
							Player p = EntityArgument.getPlayer(context, "player");

							PlayerInfoController.get(p).doPromote = false;

							return 1;
						}
					)
				)
				)
		);
	}
}
