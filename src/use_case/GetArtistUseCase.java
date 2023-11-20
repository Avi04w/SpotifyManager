package use_case;

import api.Authorization;
import entity.Artist;
import api.ArtistDB;

public final class GetArtistUseCase {
    private final ArtistDB artistDB;
    private final Authorization authorization;

    public GetArtistUseCase(Authorization authorization, ArtistDB artistDB) {
        this.authorization = authorization;
        this.artistDB = artistDB;
    }

    public Artist getArtist(String id) {
        return artistDB.getArtist(authorization, id);
    }
}
