package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Token {
    private static final String clientID = "6d58318288544d5cbf5059b4b9ad367f";
    private static final int port = 8888;
    private static final String redirectURI = "http://localhost:" + port + "/callback";
    private static final String scopes = "user-modify-playback-state user-read-playback-state playlist-read-private playlist-modify-private app-remote-control user-follow-read";
//    private static final String scopes = "user-read-private user-read-email";
    private static String token = "";

    private static volatile boolean tokenReceived = false;

    private static String fetchToken() throws URISyntaxException, IOException {
        // Set up local HTTP server to handle callback
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/callback", new CallbackHandler(server));
        server.setExecutor(null); // creates a default executor
        server.start();

        String autURL = "https://accounts.spotify.com/authorize" +
                "?response_type=token" +
                "&client_id=" + clientID +
                "&redirect_uri=" + redirectURI +
                "&scopes=" + URLEncoder.encode(scopes, StandardCharsets.UTF_8);

        Desktop.getDesktop().browse(new URI(autURL));

        while (!tokenReceived) {
            Thread.onSpinWait();
        }

        return token;
    }

    static class CallbackHandler implements HttpHandler {
        private final HttpServer server;
        public CallbackHandler(HttpServer server) {
            this.server = server;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<html><body><h1>Authentication successful.</h1>" +
                    "<h1>Please paste the url of this page into the program.</h1>" +
                    "<h1>You can then close this tab.</h1></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

//            URI requestedUri = exchange.getRequestURI();
//            String fragment = requestedUri.getFragment();
//
//            if (fragment != null) {
//                String[] params = fragment.split("&");
//                for (String param : params) {
//                    String[] keyValue = param.split("=");
//                    if (keyValue.length == 2 && keyValue[0].equals("access_token")) {
//                        String accessToken = keyValue[1];
//                        System.out.println("Access Token: " + accessToken);
//                        // Use this access token for further requests to the Spotify API
//                        break;
//                    }
//                }
//            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the URL you were redirected to:");
            String redirectUrl = scanner.nextLine();
            String[] parts = redirectUrl.split("[#&]");
            String accessToken = "";
            for (String part : parts) {
                if (part.startsWith("access_token=")) {
                    token = part.substring(13);
                    tokenReceived = true;
                    server.stop(0);
                    break;
                }
            }
        }
    }

    public String getToken() throws URISyntaxException, IOException {
        if (tokenReceived){
            return token;
        }
        return fetchToken();
    }
}
