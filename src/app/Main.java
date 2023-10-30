package app;

import api.MongoArtistDB;
import use_case.*;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        GetArtistUseCase getArtistUseCase = config.getUserUseCase();

        System.out.println(getArtistUseCase.getArtist());
    }
}
