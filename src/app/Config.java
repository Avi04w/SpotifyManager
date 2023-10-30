package app;

import api.MongoArtistDB;
import api.ArtistDB;
import use_case.GetArtistUseCase;

public class Config {

    private final ArtistDB artistDB = new MongoArtistDB();

    public GetArtistUseCase getUserUseCase() {
        return new GetArtistUseCase(artistDB);
    }
}
