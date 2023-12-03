import data_access.Authorization;
import data_access.Token;
import static org.junit.Assert.*;

public class TokenTest {
    static String code = "AQDazq1jhZ7BvshaurxiKjeylshsHQvOt287ImJMyN9nqABCTcc5Nvg3keAN9u01CPo8kRi7vz1Q_URqyjXzVsPHFZZDQrLvJ4CnAfz4mXL5Mxo3zJJ7VM310QkEpr3QvUwFbiXXJ0mI9qAp2EEfitwVWsEIvaGWZclNvX1fyUuFffbQfyjQUBuPHtO7h33PTDHZScrT45r8EGX9zMTXIV8fwkKx2qPhqly6NJkGxCKQ48c-YzSII6BWUzqfUcq38lgcwO4AjdWNz3a8JFvobTBgicCaKMvru0zGg0YdNQaPEPH9m8jSdmg3i0hwnQRVOkaC36146VNNRtVsQpO0HKVnOIs2vfp1O6Qz_KTkNUx6f5-I8kfuV1PpTOKWWJvVLUBNlqViOtYpDT1vfw07VZCWQfd5HW2jDfWYCyF4iceyMP88bLIPgROypW3rPQqEUSgugxeRM7Lo";
    //CHANGE THIS EVERY TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token;

    @org.junit.BeforeClass
    public static void setSpotifyApiObject(){
        token = new Token();
    }

    @org.junit.Test
    public void testGetAuthURI() {
        String authUri = token.getAuthURI();
        assertEquals( "https://accounts.spotify.com:443/authorize?client_id=6d58318288544d5cbf5059b4b9ad367f&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Fcallback&scope=user-modify-playback-state%20user-top-read%20user-read-playback-state%20playlist-read-private%20playlist-modify-private%20playlist-modify-public%20app-remote-control%20user-follow-read%20user-read-private%20user-read-email&show_dialog=true", authUri);
    }

    @org.junit.Test
    public void testSetToken() {
        assert (token.setAccessAndRefreshToken(code).contains("Token Expires in: "));
    }
}
