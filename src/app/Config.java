package app;

import data_access.*;
import use_case.album.AlbumDataAccessInterface;
import use_case.album.AlbumOutputData;
import use_case.artist.ArtistDataAccessInterface;
import use_case.artist.ArtistOutputData;
import use_case.player.PlayerDataAccessInterface;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

public class Config {

    private final ArtistDataAccessInterface artistDB = new ArtistDAO();
    private final PlayerDataAccessInterface playerDB = new PlayerDAO();
    private final AlbumDataAccessInterface albumDB = new AlbumDAO();

    public ArtistOutputData getArtistUseCase(Authorization authorization) {
        return new ArtistOutputData(authorization, artistDB);
    }

    public AlbumOutputData getAlbumUseCase(Authorization authorization) {
        return new AlbumOutputData(authorization, albumDB);
    }

    public PlayerInputData getPlayerInputData(Authorization authorization){
        return new PlayerInputData(authorization, playerDB);
    }

    public PlayerOutputData getPlayerOutputData(Authorization authorization){
        return new PlayerOutputData(authorization, playerDB);
    }
}
