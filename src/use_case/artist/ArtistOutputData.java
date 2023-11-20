package use_case.artist;

import data_access.Authorization;
import entity.Artist;

public final class ArtistOutputData {
    private final ArtistDataAccessInterface artistDB;
    private final Authorization authorization;

    public ArtistOutputData(Authorization authorization, ArtistDataAccessInterface artistDB) {
        this.authorization = authorization;
        this.artistDB = artistDB;
    }

    public Artist getArtist(String id) {
        return artistDB.getArtist(authorization, id);
    }
}
