package entity;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private String id;
    private String uri;
    private ArrayList<Artist> artists;
    private String type;
    private ArrayList<String> image;
    private int totalTracks;
    private ArrayList<Track> tracks;
    private ArrayList<String> genres;
    private int popularity;

    public Album(String name, String id, String uri, ArrayList<Artist> artists, String type, ArrayList<String> image, int totalTracks, ArrayList<Track> tracks, ArrayList<String> genres, int popularity) {
        this.name = name;
        this.id = id;
        this.uri = uri;
        this.artists = artists;
        this.type = type;
        this.image = image;
        this.totalTracks = totalTracks;
        this.tracks = tracks;
        this.genres = genres;
        this.popularity = popularity;
    }

    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    public static class AlbumBuilder {
        private String name;
        private String id;
        private String uri;
        private ArrayList<Artist> artists;
        private String type;
        private ArrayList<String> image;
        private int totalTracks;
        private ArrayList<Track> tracks;
        private ArrayList<String> genres;
        private int popularity;
        AlbumBuilder(){
        }

        public AlbumBuilder name(String name){
            this.name = name;
            return this;
        }
        public AlbumBuilder id(String id){
            this.id = id;
            return this;
        }
        public AlbumBuilder uri(String uri){
            this.uri = uri;
            return this;
        }
        public AlbumBuilder artists (ArrayList<Artist> artists){
            this.artists = artists;
            return this;
        }
        public AlbumBuilder type (String type){
            this.type = type;
            return this;
        }
        public AlbumBuilder image (ArrayList<String> image){
            this.image = image;
            return this;
        }
        public AlbumBuilder totalTracks(int totalTracks){
            this.totalTracks = totalTracks;
            return this;
        }
        public AlbumBuilder tracks(List<String> tracks){
            this.tracks = tracks;
            return this;
        }
        public AlbumBuilder genres(ArrayList<String> genres){
            this.genres = genres;
            return this;
        }
        public AlbumBuilder popularity(int popularity){
            this.popularity = popularity;
            return this;
        }
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getUri() { return uri; }
    public ArrayList<Artist> getArtists() { return artists; }
    public String getType() { return type; }
    public ArrayList<String> getImage() { return image; }
    public int getTotalTracks() { return totalTracks; }

    public ArrayList<Track> getTracks() { return tracks; }

    public ArrayList<String> getGenres() { return genres; }

    public int getPopularity() { return popularity; }
}
