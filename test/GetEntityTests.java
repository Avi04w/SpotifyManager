import data_access.*;
import entity.Album;
import entity.Artist;
import entity.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetEntityTests {
    static String code = "AQAFTcISWsRsDAX62RK4EAgVj9Z6-oY-0iaO97onxlSQxBle-TxcN4YtesFl9Ejx9DyMdHizhw7ecOTHk8fybBxbEVpkTJ5gvxL53tS31sATqv0xtNHWwqJ0RXuBLNylw03tTsEJh3E1BDUPg41C4GN1TCuiWMT4BtDe-gltuUC203OdIpvlGcC_InMTlshdfbingiCWteU8Rm1-eaGeV8RQfUBJtUYiXPSMOHvv_IgO2pnKO5M_1HfrftEIUmo8Zi_AKboGG9BFc9b0jFQRLK51ANOS67DEmcJi2CACZJGJ6B25F1khn7hjuy7gIzL8gAyaj2hhUXgTofDiD2kBYQP1XN6cAgVOsjrU3lxevdReEkNsL9TZ-6ssyrFHBQbqmmOe0x5KJc_03i6oDcvxeH_P7SOH4GI_kPnAPzrYg1mfI-PC1hWICD6DegcmHImRq4BDEd6fRK4p";
    //CHANGE THIS TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token = new Token();
    static ArtistDAO artistDao = new ArtistDAO();
    static AlbumDAO albumDao = new AlbumDAO();
    static TrackDAO trackDao = new TrackDAO();

    @org.junit.BeforeClass()
    public static void setToken(){
        token.setAccessAndRefreshToken(code);
    }

    @org.junit.Test
    public void getTrackTest(){
        try {
            Track track = trackDao.getTrack(token, "11dFghVXANMlKmJXsNCbNl");
            assertEquals("Cut To The Feeling", track.getName());
        }
        catch(RuntimeException e) {
            fail("Error getting Track");
        }
    }

    @org.junit.Test
    public void getArtistTest(){
        try {
            Artist artist = artistDao.getArtist(token, "0TnOYISbd1XYRBk9myaseg");
            assertEquals("Pitbull", artist.getName());
        }
        catch(RuntimeException e) {
            fail("Error getting Artist");
        }
    }

    @org.junit.Test
    public void getAlbumTest(){
        try {
            Album album = albumDao.getAlbum(token, "4aawyAB9vmqN3uQ7FjRGTy");
            assertEquals("Global Warming", album.getAlbumName());
        }
        catch(RuntimeException e) {
            fail("Error getting Album");
        }
    }
}
