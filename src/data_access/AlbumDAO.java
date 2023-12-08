package data_access;

import entity.Album;
import entity.Artist;
import entity.Track;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.album.AlbumDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class AlbumDAO implements AlbumDataAccessInterface {
    /**
     * Retrieves information about the album.
     * The method sends a request to the Spotify API endpoint "https://api.spotify.com/v1/albums" using the
     * provided authorization token. It processes the API response to construct and return an Album object
     * containing information such as the Album name, ID, URI, Artists, type, image, and more.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param id The ID of the Album is used to get the Album correctly.
     * @return An Album object representing the details of the album.
     * @throws JSONException If there is an issue parsing the JSON response from the Spotify API.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or if the album cannot be retrieved.
     */
    @Override
    public Album getAlbum(Authorization authorization, String id) throws JSONException {

        String url = "https://api.spotify.com/v1/albums/";

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

                String image = responseBody.getJSONArray("images").getJSONObject(0).getString("url");

                JSONArray genresJSON = responseBody.getJSONArray("genres");
                ArrayList<String> genres = new ArrayList<>();
                for (int i = 0; i < genresJSON.length(); i++){
                    genres.add(genresJSON.getString(i));
                }

                return Album.builder()
                        .name(responseBody.getString("name"))
                        .id(responseBody.getString("id"))
                        .uri(responseBody.getString("uri"))
                        .artists(artists)
                        .type(responseBody.getString("album_type"))
                        .image(image)
                        .totalTracks(responseBody.getInt("total_tracks"))
                        .genres(genres)
                        .popularity(responseBody.getInt("popularity"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}


