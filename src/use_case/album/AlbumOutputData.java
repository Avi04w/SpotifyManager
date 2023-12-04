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

    public Album getAlbum(String id) {
        return albumDB.getAlbum(authorization, id);
    }
}


