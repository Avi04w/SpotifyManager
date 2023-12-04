package use_case.album;

import data_access.Authorization;
import entity.Album;

public interface AlbumDataAccessInterface {
    Album getAlbum(Authorization authorization, String id);
}
