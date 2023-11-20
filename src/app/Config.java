package app;

import api.*;
import use_case.*;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

public class Config {

    private final ArtistDB artistDB = new MongoArtistDB();
    private final PlayerDB playerDB = new MongoPlayerDB();
    private final AlbumDB albumDB = new MongoAlbumDB();

    public GetArtistUseCase getArtistUseCase(Authorization authorization) {
        return new GetArtistUseCase(authorization, artistDB);
    }

    public GetAlbumUseCase getAlbumUseCase(Authorization authorization) {
        return new GetAlbumUseCase(authorization, albumDB);
    }

    public PlayerInputData getPlayerInputData(Authorization authorization){
        return new PlayerInputData(authorization, playerDB);
    }

    public PlayerOutputData getPlayerOutputData(Authorization authorization){
        return new PlayerOutputData(authorization, playerDB);
    }
}
