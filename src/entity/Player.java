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

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {
        private String name;
        private boolean isPlaying;
        private Album album;
        private ArrayList<Artist> artists;
        private int trackLength;
        private boolean explicit;
        private String trackId;
        private String href;

        PlayerBuilder() {
        }

        public PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder isPlaying(boolean isPlaying) {
            this.isPlaying = isPlaying;
            return this;
        }

        public PlayerBuilder album(Album album){
            this.album = album;
            return this;
        }

        public PlayerBuilder artists(ArrayList<Artist> artists){
            this.artists = artists;
            return this;
        }

        public PlayerBuilder trackLength(int trackLength){
            this.trackLength = trackLength;
            return this;
        }

        public PlayerBuilder explicit(boolean explicit){
            this.explicit = explicit;
            return this;
        }

        public PlayerBuilder trackId(String trackId){
            this.trackId = trackId;
            return this;
        }

        public PlayerBuilder href(String href) {
            this.href = href;
            return this;
        }

        public Player build() {
            return new Player(name, isPlaying, album, artists, trackLength, explicit, trackId, href);
        }
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
