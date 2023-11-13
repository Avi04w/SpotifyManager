package use_case;

import api.Authorization;
import entity.Artist;
import api.ArtistDB;

public final class GetArtistUseCase {
    private final ArtistDB artistDB;
    private Authorization authorization;
    private String id;

    public GetArtistUseCase(ArtistDB artistDB, Authorization authorization, String id) {
        this.artistDB = artistDB;
        this.authorization = authorization;
        this.id = id;
    }

    public Artist getArtist() {
        return artistDB.getArtist(authorization, id);
    }
}
