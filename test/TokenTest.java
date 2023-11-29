import data_access.Authorization;
import data_access.Token;
import static org.junit.Assert.*;

public class TokenTest {
    static String code = "AQCTX13ReiRUfDJ7_yS6K_tKA3QZjRbLAiQn2u2XEaJnRaQZbHh9wGvPLHFjdycBRfnCu128m8jAI12nDp77Q9Fd7NBlnO4rH-E0TPtOzJwEN00B9x3bmS5rgWG96gzfItbwg5s5zsZKnzuMxx2jkZrv38W7NK8xVYnM4lZCGVbtsNxLlDFy2EBNlqOyG1sc4VwvQo3wXQ_1fzGN4Uy-DDGMXzkSw757d8Lw3XYSCIXtkXqk6MuCUGXK7YrTHJecDpeHPbBwVPJWPJvmAokiqOx2HxRq3PJolLUhX1N8_pNj5H3woB_KEPhnkfyrxEQaGdwIL7CSu4nKAqi-D2xO-ck-dePTSFvKdiIckxv0EsTu-0RM7caWbt3ca33A9uOaY4a8ENQDrLfquH9H8KWHgHhjzIqoOK5bsnYIpvjpBN4aiSZiHzoco1UGR-Z4eMhzbN9aGDTVLR5V";
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
        try{
            token.setAccessAndRefreshToken(code);
        } catch (RuntimeException e) {
            fail("Error setting token.");
        }

    }
}
