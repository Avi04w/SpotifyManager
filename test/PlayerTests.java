import data_access.Authorization;
import data_access.PlayerDAO;
import data_access.Token;
import entity.Track;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.*;

public class PlayerTests {
    static String code = "AQAKz1ySL9OwGvpi7RZvlBnPUkjobJpHUDX6aZw55-UOa30ogF6QdIbJii580vLc9SKHxIbqHax-rdRztjnL0QzhptS3lI4ImZ2lx6AfqX-ubadjxUQl2eXhORGtyN5HD9lxT0zTwDny1232sJFmPleZU6dXk_PeS-TC3nUdWi-TaIGDXxJWzzE3WDjtyK5dkNzOAUmURtLioIbCXFTOrP0ZjimnDDymyUBiiqsfEF5bfU7--E_e7_iCKCcuXLNupiUdEfN8KUmRQFsGrFWm8ppnadoVVwPE6SkaIdSc3SB2Q5G5W-1pTVjNEWtSM9QP-ZO95hyBf0f_uGPKRwLQmxuF3K1xINeJ1G1ZKFexqWnfFtqmnoYkLrTfxNhy5M0aM-UUwHAgWMZfm0bE7gx_YlMtXwZL0WxdhO1Lsdcv1jNsK3uN8bZKrjDvfUg58r9vSwGzuQxL4Iuo";
    //CHANGE THIS TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token = new Token();
    static PlayerDAO playerDao = new PlayerDAO();
    static PlayerInputData playerInputData;
    static PlayerOutputData playerOutputData;
    static String device = "c4c72f8f96e568165c2727ac6551bb31974c8883";
    //CURRENTLY SET TO AVI'S MACBOOK ID, CHANGE IT TO YOURS IF YOU WANT TO RUN THIS TEST

    @org.junit.BeforeClass()
    public static void setToken(){
        token.setAccessAndRefreshToken(code);
        playerInputData = new PlayerInputData(token, playerDao);
        playerOutputData = new PlayerOutputData(token, playerDao);
    }

    @org.junit.Test
    public void getAvailableDeviceTest(){
        try {
            String deviceId = playerOutputData.getAvailableDevice(token);
            assertEquals(device, deviceId);
        }
        catch (RuntimeException e){
            fail("Error with getting available device.");
        }
    }

    @org.junit.Test
    public void getPlayerTest(){
        try {
            String deviceId = playerOutputData.getAvailableDevice(token);
        }
        catch (RuntimeException e){
            fail("Error with getting available device.");
        }
    }

    @org.junit.Test
    public void getQueueTest(){
        try {
            ArrayList<Track> queue = playerOutputData.getQueue(token);
        } catch (RuntimeException e) {
            fail("Error with getting queue");
        }
    }

    @org.junit.Test
    public void resumeTest(){
        try {
            playerInputData.resume(token, device);
        } catch (RuntimeException e) {
            fail("Error with resuming playback");
        }
    }

    @org.junit.Test
    public void pauseTest(){
        try {
            playerInputData.pause(token, device);
        } catch (RuntimeException e) {
            fail("Error with pausing playback");
        }
    }

    @org.junit.Test
    public void skipTest(){
        try {
            playerInputData.skip(token, device);
        } catch (RuntimeException e) {
            fail("Error with skipping song");
        }
    }

    @org.junit.Test
    public void previousTest(){
        try {
            playerInputData.previous(token, device);
        } catch (RuntimeException e) {
            fail("Error with going to previous song");
        }
    }

    @org.junit.Test
    public void setVolumeTest(){
        try {
            playerInputData.setVolume(token, 10, device);

            try {
                // Wait for 5 seconds (5000 milliseconds)
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int playerVol = playerOutputData.getPlayer(token).getVolume();
            assertEquals(10, playerVol);
        } catch (RuntimeException e) {
            fail("Error with setting volume");
        }
    }

    @org.junit.Test
    public void toggleShuffleTest(){
        //TURN OFF TOGGLE BEFORE YOU RUN THIS TEST
        try {
            playerInputData.toggleShuffle(token, true, device);

            // Wait for 5 seconds (5000 milliseconds)
            Thread.sleep(10000);

            boolean shuffle = playerOutputData.getPlayer(token).isShuffle();
            assertTrue(shuffle);
        } catch (RuntimeException e) {
            fail("Error with toggle");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
