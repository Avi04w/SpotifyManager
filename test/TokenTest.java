import data_access.Authorization;
import data_access.Token;
import static org.junit.Assert.*;

public class TokenTest {
    static String code = "AQB92Poc6GQPOHCRpX32VXbklT9iWAAj24vKyVOklxXImSV0OlsH2mDPpgzHKXerpXzIqLaLQnV_7BricCmdnD0V4WnI09Sc_ao5icFnx1VLxZUyPXpbmwBD1PVIksMJfLfwuSfzuuquROaxKzDvddMcxfpUAt9y8BsX8W1nMXDSALaTOd_pUEkf4DpqoNLJmfMLYduyvoMfhGWdBSfeWS87u8QLVD-3KUMR3DyN-Ex9sGTOq2_MXUw5kqxKdJiVKyPb0DaUVytPo7kILFoplOMLpvqyRTfEDIcHDlYXWXOeKmKWDpkl47qLqWowBlzSEAuXKYHRCXyhuDsldU9oa6lXlaP8rcNn7fzyIinB-7PEZYdQ1peFfu4iXOYVqy--98UcIJq5D9KHweDTWVQqHodOSoBmum5lVjE5o2Y2hMnoVJr7N5xS_AqVKCueV6ZfmBOKwAWpZioo";
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
