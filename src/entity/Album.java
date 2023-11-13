package entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Album {
    private String albumID;
    private String albumName;
    private String albumURI;
    private String albumType;
    private int totalTracks;
    private ArrayList<Track> albumTracks;
    private ArrayList<String> albumGenres;
    private int albumPopularity;

    public Album(String albumID, String albumName, String albumURI, String albumType, int totalTracks, ArrayList<Track> albumTracks, ArrayList<String> albumGenres, int albumPopularity) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumURI = albumURI;
        this.albumType = albumType;
        this.totalTracks = totalTracks;
        this.albumTracks = albumTracks;
        this.albumGenres = albumGenres;
        this.albumPopularity = albumPopularity;
    }

    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    public static class AlbumBuilder {
        private String albumID;
        private String albumName;
        private String albumURI;
        private String albumType;
        private int totalTracks;
        private ArrayList<Track> albumTracks;
        private ArrayList<String> albumGenres;
        private int albumPopularity;
        AlbumBuilder(){
        }

        public AlbumBuilder setAlbumID(String albumID){
            this.albumID = albumID;
            return this;
        }
        public AlbumBuilder setAlbumName(String albumName){
            this.albumName = albumName;
            return this;
        }
        public AlbumBuilder setAlbumURI(String albumURI){
            this.albumURI = albumURI;
            return this;
        }
        public AlbumBuilder setAlbumType(String albumType){
            this.albumType = albumType;
            return this;
        }
        public AlbumBuilder setTotalTracks(int totalTracks){
            this.totalTracks = totalTracks;
            return this;
        }
        public AlbumBuilder setAlbumTracks(ArrayList<Track> albumTracks){
            this.albumTracks = albumTracks;
            return this;
        }
        public AlbumBuilder setAlbumGenres(ArrayList<String> albumGenres){
            this.albumGenres = albumGenres;
            return this;
        }
        public AlbumBuilder setAlbumPopularity(int albumPopularity){
            this.albumPopularity = albumPopularity;
            return this;
        }
    }

    public String getAlbumID() { return albumID; }
    public String getAlbumName() { return albumName; }
    public String getAlbumURI() { return albumURI; }
    public String getAlbumType() { return albumType; }
    public int getTotalTracks() { return totalTracks; }

    public ArrayList<Track> getAlbumTracks() { return albumTracks; }

    public ArrayList<String> getAlbumGenres() { return albumGenres; }

    public int getAlbumPopularity() { return albumPopularity; }
}
