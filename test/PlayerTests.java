import data_access.Authorization;
import data_access.PlayerDAO;
import data_access.Token;
import entity.Track;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTests {
    static String code = "AQAajidT1CnCJAh0QX8ZjwiXlZhx6vCB76BpeOsmWMEaaQoNe4EVjcvQrwT-SLVn0tzZIooB23rZkM1fLWn8UnXFfO40Up23f6dEcEJzwM17aSGIdIg5d1QmsZofMGtVp0GG2cTrG3hygjazHxZwXZuJBl8plOHcqtXv40VNbrF2z3QwU0VH08rRCsU7gY7pRNIOByr14egA5tT14kT4cOvt0RgnQfOPDLIIC5d53mTTUA3RJ-IoP_UC1a0w6pISXEjFOtJt1mLuPPOaw7GY0iHi6UJD5Bi4-vjNh7B-3pfq-yoycWBeoAs-SO3goVSEsDiVY5QtDV9VXwC5rRvZzzAdwBfsNiz35Z7gKaXbjkNSQG6oYspxji3_nFQR3WWu_zlBXKdHadoKou2pTZzn51ik0h5ienlSPrcT3WdFG2pUP7cyPZvJ-oC3O7z60aawA4muzD8uCeCH";
    //CHANGE THIS TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token = new Token();
    static PlayerDAO playerDao = new PlayerDAO();
    static String device = "c4c72f8f96e568165c2727ac6551bb31974c8883";
    //CURRENTLY SET TO AVI'S MACBOOK ID, CHANGE IT TO YOURS IF YOU WANT TO RUN THIS TEST

    @org.junit.BeforeClass()
    public static void setToken(){
        token.setAccessAndRefreshToken(code);
    }

    @org.junit.Test
    public void getAvailableDeviceTest(){
        try {
            String deviceId = playerDao.getAvailableDevice(token);
        }
        catch (RuntimeException e){
            fail("Error with getting available device.");
        }
    }

    @org.junit.Test
    public void getPlayerTest(){
        try {
            String deviceId = playerDao.getAvailableDevice(token);
        }
        catch (RuntimeException e){
            fail("Error with getting available device.");
        }
    }

    @org.junit.Test
    public void getQueueTest(){
        try {
            ArrayList<Track> queue = playerDao.getQueue(token);
        } catch (RuntimeException e) {
            fail("Error with getting queue");
        }
    }

    @org.junit.Test
    public void resumeTest(){
        try {
            playerDao.resume(token, device);
        } catch (RuntimeException e) {
            fail("Error with resuming playback");
        }
    }

    @org.junit.Test
    public void pauseTest(){
        try {
            playerDao.pause(token, device);
        } catch (RuntimeException e) {
            fail("Error with pausing playback");
        }
    }

    @org.junit.Test
    public void skipTest(){
        try {
            playerDao.skip(token, device);
        } catch (RuntimeException e) {
            fail("Error with skipping song");
        }
    }

    @org.junit.Test
    public void previousTest(){
        try {
            playerDao.previous(token, device);
        } catch (RuntimeException e) {
            fail("Error with going to previous song");
        }
    }

    @org.junit.Test
    public void setVolumeTest(){
        try {
            playerDao.setVolume(token, 10, device);
            int playerVol = playerDao.getPlayer(token).getVolume();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            assertEquals(10, playerVol);
        } catch (RuntimeException e) {
            fail("Error with resume");
        }
    }

    @org.junit.Test
    public void toggleShuffleTest(){
        //TURN OFF TOGGLE BEFORE YOU RUN THIS TEST
        try {
            playerDao.toggleShuffle(token, true, device);
            boolean shuffle = playerDao.getPlayer(token).isShuffle();
            assertTrue(shuffle);
        } catch (RuntimeException e) {
            fail("Error with resume");
        }
    }
}
