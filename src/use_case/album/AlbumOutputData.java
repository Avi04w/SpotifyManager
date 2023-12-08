package use_case.album;

import data_access.Authorization;
import entity.Album;


public class AlbumOutputData {
    private final AlbumDataAccessInterface albumDB;
    private final Authorization authorization;

    public AlbumOutputData(Authorization authorization, AlbumDataAccessInterface albumDB) {
        this.albumDB = albumDB;
        this.authorization = authorization;
    }
    /**
     * Retrieves an album based on the provided identifier.
     *
     * @param id The unique identifier of the album to retrieve.
     * @return The Album object representing the requested album.
     * @see AlbumDataAccessInterface
     * @see Authorization
     */
    public Album getAlbum(String id) {
        return albumDB.getAlbum(authorization, id);
    }
}


