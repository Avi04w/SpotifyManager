package use_case;

import api.Authorization;
import entity.Album;
import api.AlbumDB;

public class GetAlbumUseCase {
    private final AlbumDB albumDB;

    public GetAlbumUseCase(AlbumDB albumDB) {
        this.albumDB = albumDB;

    }

    public Album getAlbum(Authorization authorization, String id) {
        return albumDB.getAlbum(authorization, id);
    }
}

