package use_case;

import api.Authorization;
import entity.Artist;
import api.ArtistDB;

public final class GetArtistUseCase {
    private final ArtistDB artistDB;

    public GetArtistUseCase(ArtistDB artistDB) {
        this.artistDB = artistDB;
    }

    public Artist getArtist(Authorization authorization, String id) {
        return artistDB.getArtist(authorization, id);
    }
}
