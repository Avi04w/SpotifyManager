import data_access.*;
import entity.Album;
import entity.Artist;
import entity.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetEntityTests {
    static String code = "AQAKz1ySL9OwGvpi7RZvlBnPUkjobJpHUDX6aZw55-UOa30ogF6QdIbJii580vLc9SKHxIbqHax-rdRztjnL0QzhptS3lI4ImZ2lx6AfqX-ubadjxUQl2eXhORGtyN5HD9lxT0zTwDny1232sJFmPleZU6dXk_PeS-TC3nUdWi-TaIGDXxJWzzE3WDjtyK5dkNzOAUmURtLioIbCXFTOrP0ZjimnDDymyUBiiqsfEF5bfU7--E_e7_iCKCcuXLNupiUdEfN8KUmRQFsGrFWm8ppnadoVVwPE6SkaIdSc3SB2Q5G5W-1pTVjNEWtSM9QP-ZO95hyBf0f_uGPKRwLQmxuF3K1xINeJ1G1ZKFexqWnfFtqmnoYkLrTfxNhy5M0aM-UUwHAgWMZfm0bE7gx_YlMtXwZL0WxdhO1Lsdcv1jNsK3uN8bZKrjDvfUg58r9vSwGzuQxL4Iuo";
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
