package app;

import api.*;
import use_case.GetArtistUseCase;
import use_case.GetPlayerUseCase;

public class Config {

    private final ArtistDB artistDB = new MongoArtistDB();
    private final PlayerDB playerDB = new MongoPlayerDB();

    public GetArtistUseCase getArtistUseCase(Authorization authorization, String id) {
        return new GetArtistUseCase(artistDB, authorization, id);
    }

    public GetPlayerUseCase getPlayerUseCase(Authorization authorization){
        return new GetPlayerUseCase(playerDB, authorization);
    }
}
