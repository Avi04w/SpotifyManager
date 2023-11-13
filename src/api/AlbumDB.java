package api;
import entity.Album;
public interface AlbumDB {
    Album getAlbum(Authorization authorization, String id);
}
