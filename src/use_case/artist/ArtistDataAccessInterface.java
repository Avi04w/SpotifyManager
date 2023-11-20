package use_case.artist;

import data_access.Authorization;
import entity.Artist;

public interface ArtistDataAccessInterface {
    Artist getArtist(Authorization authorization, String id);
}
