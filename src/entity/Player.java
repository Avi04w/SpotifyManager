package entity;

import java.util.ArrayList;

public class Player {
    private String name;
    private boolean isPlaying;
    private Album album;
    private ArrayList<Artist> artists;
    private int trackLength;
    private boolean explicit;
    private String trackId;
    private String href;

    public Player(String name, boolean isPlaying, Album album, ArrayList<Artist> artists, int trackLength,
                  boolean explicit, String trackId, String href) {
        this.name = name;
        this.isPlaying = isPlaying;
        this.album = album;
        this.artists = artists;
        this.trackLength = trackLength;
        this.explicit = explicit;
        this.trackId = trackId;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Album getAlbum() {
        return album;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getHref(){
        return href;
    }

    @Override
    public String toString() {
        return "Player{" +
                "trackName='" + name +
                ", artists='" + artists.toString() +
                ", album=" + album.toString() +
                ", trackLength=" + trackLength +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
