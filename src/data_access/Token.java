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
    private static final String clientId = "86c2fb155ea544b7b9a979c8be45f165";
    private static final String clientSecret = "a7db6b8d55794d1e9dd345a4d12ab08e";
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

    /**
     * Sets the access and refresh tokens in the associated SpotifyApi object based on the provided authorization code.
     * Initiates an authorization code request to the Spotify API, retrieves the access and refresh tokens,
     * and updates the SpotifyApi object accordingly.
     *
     * @param code The authorization code used to obtain access and refresh tokens.
     * @return A message indicating the success or failure of the token retrieval process.
     *         If successful, the message includes the expiration time of the access token.
     *         If an error occurs, the message contains an error description.
     */
    public String setAccessAndRefreshToken(String code) {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            //Set access and refresh tokens
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            return ("Token Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return ("Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the authorization URI based on the configured authorization code request.
     * Initiates an authorization code URI request to the Spotify API and returns the resulting URI as a string.
     *
     * @return A string representation of the authorization URI.
     * @throws IOException If there is an issue executing the authorization code URI request.
     */
    public String getAuthURI() {
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    /**
     * Retrieves the associated SpotifyApi Object instance.
     *
     * @return The SpotifyApi instance associated with this object.
     */
    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}
