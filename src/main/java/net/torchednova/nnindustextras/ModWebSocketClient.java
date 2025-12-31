package net.torchednova.nnindustextras;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ModWebSocketClient implements WebSocket.Listener {

    private WebSocket socket;

    public void connect() {
        HttpClient client = HttpClient.newHttpClient();

        client.newWebSocketBuilder()
                .buildAsync(URI.create("wss://echo.websocket.org"), this)
                .thenAccept(ws -> {
                    this.socket = ws;
                    System.out.println("[MyMod] WebSocket connected");
                })
                .exceptionally(err -> {
                    err.printStackTrace();
                    System.out.println("[MyMod] WebSocket FAILED");
                    return null;
                });

        System.out.println("Hello from inside the websocket builder\nThe Web is curently " + client);
    }

    public void send(String message) {
        if (socket != null) {
            socket.sendText(message, true);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println("[MyMod] Received: " + data);
        webSocket.request(1);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        error.printStackTrace();
    }
}
