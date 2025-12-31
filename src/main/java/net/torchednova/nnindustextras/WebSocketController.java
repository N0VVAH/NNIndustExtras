package net.torchednova.nnindustextras;

import net.neoforged.fml.common.Mod;
import net.torchednova.nnindustextras.NNIndustExtras;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.torchednova.nnindustextras.ModWebSocketClient;

@Mod(NNIndustExtras.MODID)
public class WebSocketController {

    private static ModWebSocketClient WS = null;

    public static void onServerStarting(ServerStartingEvent event) {
        if (event.getServer().isDedicatedServer()) {
            System.out.println("In Start event");
            WS = new ModWebSocketClient();
            if (WS == null) return;
            WS.connect();
        }
    }
}
