package app;

import api.*;
import use_case.*;

public class Config {

    private final ArtistDB artistDB = new MongoArtistDB();
    private final PlayerDB playerDB = new MongoPlayerDB();

    public GetArtistUseCase getArtistUseCase() {
        return new GetArtistUseCase(artistDB);
    }

    public PlayerInputData getPlayerUseCases(Authorization authorization){
        return new PlayerInputData(authorization, playerDB);
    }
}
