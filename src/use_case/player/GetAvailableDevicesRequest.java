package use_case.player;

import data_access.Authorization;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public abstract class GetAvailableDevice implements PlayerDataAccessInterface {
    public ArrayList<String> GetAvailableDevice(Authorization authorization) throws JSONException {
        String url = "https://api.spotify.com/v1/me/player/devices";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray devicesJSON = responseBody.getJSONArray("devices");
                ArrayList<String> devices = new ArrayList<>();
                for (Map<String, String> map : devicesJSON) {
                    String device = map.get("name");
                    devices.add(device);
                }
                return devices;
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));

            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
