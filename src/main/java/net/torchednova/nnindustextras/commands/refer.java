package net.torchednova.nnindustextras.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.tom.storagemod.platform.GameObject;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.torchednova.nnindustextras.referrals.GivesManager;
import net.torchednova.nnindustextras.referrals.ReferralManager;
import net.torchednova.nnindustextras.savedata.TargetDataStorage;


public class refer {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("referral")
                        // /referral get
                        .then(
                                Commands.literal("get")
                                        .executes(context -> {
                                            String referCode = ReferralManager.getCode(context.getSource().getEntity());
                                            context.getSource().sendSuccess(
                                                    () -> Component.literal("Your Code id: " + referCode),
                                                    false
                                            );
                                            return 1;
                                        })
                        )

                        // /referral enter <code>
                        .then(
                                Commands.literal("enter")
                                        .then(
                                                Commands.argument("code", StringArgumentType.greedyString())
                                                        .executes(context -> {
                                                            String code = StringArgumentType.getString(context, "code");

                                                            Boolean worked = ReferralManager.addReferry(context.getSource().getEntity(), code);
                                                            if (worked)
                                                            {
                                                                context.getSource().sendSuccess(
                                                                        () -> Component.literal("Your referral has been set here is your reward!"),
                                                                        false
                                                                );
                                                                if (context.getSource().getServer().getPlayerList().getPlayer(ReferralManager.getReferral(code).uuid) != null) {
                                                                    var disp = context.getSource().getServer().getCommands().getDispatcher();
                                                                    ParseResults<CommandSourceStack> parse = disp.parse("give " + context.getSource().getDisplayName() + " gachamachine:gacha_coin 5", context.getSource());

                                                                    context.getSource().getServer().getCommands().performCommand(parse, "");
                                                                }
                                                                else
                                                                {
                                                                    GivesManager.addGive(ReferralManager.getReferral(code).uuid);
                                                                    TargetDataStorage.saveGives(context.getSource().getServer());
                                                                }
                                                            }
                                                            else
                                                            {
                                                                context.getSource().sendSuccess(
                                                                        () -> Component.literal("Your code was invalid / deactivated or you have already entered a code"),
                                                                        false
                                                                );
                                                            }

                                                            return 1;
                                                        })
                                        )
                        )
        );
    }
}


