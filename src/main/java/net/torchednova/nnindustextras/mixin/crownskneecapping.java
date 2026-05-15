package net.torchednova.nnindustextras.mixin;

import com.rae.crowns.content.fields.util.PhysicsWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(com.rae.crowns.content.fields.temperature.TemperatureTicker.class)
public class crownskneecapping {

	@Inject(
		method = "tick",
		at = @At("HEAD"),
		cancellable = true
	)
	private static void nnindustkneecapcrowns(@NotNull Set<Long> tickingSections, @NotNull PhysicsWorldData data, CallbackInfo ci)
	{
		ci.cancel();
	}
}
