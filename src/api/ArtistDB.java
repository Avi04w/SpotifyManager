package api;

import entity.Artist;

public interface ArtistDB {
    Artist getArtist(Authorization authorization, String id);
}
