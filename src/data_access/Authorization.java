package data_access;

import se.michaelthelin.spotify.SpotifyApi;

public interface Authorization {
    String setAccessAndRefreshToken(String code);
    String getAuthURI();
    SpotifyApi getSpotifyApi();
}
