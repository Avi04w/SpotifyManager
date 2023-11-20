package entity;
import java.util.ArrayList;

public class Track {

    private Album album;
    private ArrayList<Artist> artists;
    private int duration_ms;
    private boolean explicit;
    private String id;
    private String name;
    private String uri;

    public Track(Album album, ArrayList<Artist> artists, int duration_ms, boolean isExplicit, String id, String name, String uri) {
        this.album = album;
        this.artists = artists;
        this.duration_ms = duration_ms;
        this.explicit = isExplicit;
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    public static TrackBuilder builder() {
        return new TrackBuilder();
    }

    public static class TrackBuilder {
        private Album album;
        private ArrayList<Artist> artists;
        private int duration_ms;
        private boolean explicit;
        private String id;
        private String name;
        private String uri;

        TrackBuilder() {
        }

        public TrackBuilder album(Album album) {
            this.album = album;
            return this;
        }
        public TrackBuilder artists(ArrayList<Artist> artists) {
            this.artists = artists;
            return this;
        }

        public TrackBuilder duration_ms(int duration_ms) {
            this.duration_ms = duration_ms;
            return this;
        }

        public TrackBuilder explicit(boolean explicit) {
            this.explicit = explicit;
            return this;
        }

        public TrackBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TrackBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TrackBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Track build() {
            return new Track(album, artists, duration_ms, explicit, id, name, uri);
        }
    }

    @Override
    public String toString() {
        String artistNames = "";
        for (Artist artist : artists) {
            artistNames = artistNames + '\'' + artist.getName() + '\'' + ", ";
        }
        return "Your entity.Track{" +
                "album=" + album.getAlbumName() + '\'' +
                ", artists='" + artistNames +
                "durationInMs=" + Integer.toString(duration_ms) + '\'' +
                ", isExplicit=" + Boolean.toString(explicit) + '\'' +
                ", id=" + id + '\'' +
                ", trackName='" + name + '\'' +
                ", uri=" + uri + '\'' +
                '}';
    }

    public Album getAlbum() { return album; }

    public ArrayList<Artist> getArtists() { return artists; }

    public int getDuration_ms() { return duration_ms; }

    public boolean isExplicit() { return explicit; }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getUri() { return uri; }

}
