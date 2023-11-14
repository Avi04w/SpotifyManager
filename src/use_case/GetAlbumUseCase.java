package use_case;

import api.Authorization;
import entity.Album;
import api.AlbumDB;

public class GetAlbumUseCase {
    private final AlbumDB albumDB;
    private Authorization authorization;
    private String id;

    public GetAlbumUseCase(AlbumDB albumDB, Authorization authorization, String id) {
        this.albumDB = albumDB;
        this.authorization = authorization;
        this.id = id;
    }

    public Album getAlbum() {
        return albumDB.getAlbum(authorization, id);
    }
}

