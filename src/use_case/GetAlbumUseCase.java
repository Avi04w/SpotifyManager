package use_case;

import api.Authorization;
import entity.Album;
import api.AlbumDB;

public class GetAlbumUseCase {
    private final AlbumDB albumDB;
    private final Authorization authorization;

    public GetAlbumUseCase(Authorization authorization, AlbumDB albumDB) {
        this.albumDB = albumDB;
        this.authorization = authorization;
    }

    public Album getAlbum(String id) {
        return albumDB.getAlbum(authorization, id);
    }
}

