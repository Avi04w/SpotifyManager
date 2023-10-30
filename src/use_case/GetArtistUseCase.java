package use_case;

import entity.Artist;
import api.ArtistDB;

public final class GetArtistUseCase {
    private final ArtistDB artistDB;

    public GetArtistUseCase(ArtistDB artistDB) {
        this.artistDB = artistDB;
    }

    public Artist getArtist() {
        return artistDB.getArtist();
    }
}
