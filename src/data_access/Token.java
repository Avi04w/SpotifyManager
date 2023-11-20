package data_access;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.io.IOException;
import java.net.URI;

public class Token implements Authorization{
    private static final String clientId = "6d58318288544d5cbf5059b4b9ad367f";
    private static final String clientSecret = "f33f5981237b4510b01de6167b019fa1";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8888/callback");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//          .state("x4xkmn9pu3j6ukrs8n")
          .scope("user-modify-playback-state user-top-read user-read-playback-state playlist-read-private " +
                  "playlist-modify-private playlist-modify-public app-remote-control user-follow-read " +
                  "user-read-private user-read-email")
          .show_dialog(true)
            .build();

    public void setAccessAndRefreshToken(String code) {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            //Set access and refresh tokens
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Token Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getAuthURI() {
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}