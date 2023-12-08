package data_access;

import entity.Artist;
import entity.Track;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.track.TrackDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class TrackDAO implements TrackDataAccessInterface {
    /**
     * Retrieves information about a Spotify track using the provided track ID and authorization token.
     * The method sends a GET request to the Spotify API's "tracks" endpoint and parses the response
     * to construct a Track object representing the requested track.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param id             The unique identifier (ID) of the Spotify track.
     * @return A Track object containing information about the requested track.
     * @throws JSONException If there is an issue parsing the JSON response from the Spotify API.
     * @throws RuntimeException If there is an issue with the HTTP request or if the API returns an error.
     */
    @Override
    public Track getTrack(Authorization authorization, String id) throws JSONException {

        String url = "https://api.spotify.com/v1/tracks/";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url + id)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray artistsJSON = responseBody.getJSONArray("artists");
                ArrayList<Artist> artists = ArtistDAO.getArtistsArray(authorization, artistsJSON);

                return Track.builder()
                        .album(new AlbumDAO().getAlbum(authorization, responseBody.getJSONObject("album").getString("id")))
                        .artists(artists)
                        .duration_ms(responseBody.getInt("duration_ms"))
                        .explicit(responseBody.getBoolean("explicit"))
                        .id(responseBody.getString("id"))
                        .name(responseBody.getString("name"))
                        .uri(responseBody.getString("uri"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
