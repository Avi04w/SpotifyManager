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

                JSONArray tracksJSON = responseBody.getJSONObject("tracks").getJSONArray("items");
                ArrayList<Track> tracks = new ArrayList<>();

//                for (int i = 0; i < tracksJSON.length(); i++){
//                    JSONObject trackJSON = tracksJSON.getJSONObject(i);
//                    String trackId = trackJSON.getString("id");
//                    tracks.add(new MongoTrackDB().getTrack(authorization, trackId));
//                }

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
                        .tracks(tracks)
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


