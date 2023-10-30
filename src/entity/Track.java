package entity;
import java.util.ArrayList;

public class Track {
    private ArrayList<Artist> artists;
    private int duration_ms;
    private boolean explicit;
    private String id;
    private String name;
    private String uri;
    private boolean is_playable;

    public Track(ArrayList<Artist> artists, int duration_ms, boolean isExplicit, String id, String name, String uri, boolean is_playable) {
        this.artists = artists;
        this.duration_ms = duration_ms;
        this.explicit = isExplicit;
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.is_playable = is_playable;
    }

    public static TrackBuilder builder() {
        return new TrackBuilder();
    }

    public static class TrackBuilder {
        private ArrayList<Artist> artists;
        private int duration_ms;
        private boolean explicit;
        private String id;
        private String name;
        private String uri;
        private boolean is_playable;

        TrackBuilder() {
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

        public TrackBuilder is_playable(boolean is_playable) {
            this.is_playable = is_playable;
            return this;
        }

        public Track build() {
            return new Track(artists, duration_ms, explicit, id, name, uri, is_playable);
        }
    }

    @Override
    public String toString() {
        String artistNames = "";
        for (Artist artist : artists) {
            artistNames = artistNames + '\'' + artist.getName() + '\'' + ", ";
        }
        return "Your entity.Track{" +
                "artists='" + artistNames +
                "durationInMs=" + Integer.toString(duration_ms) + '\'' +
                ", isExplicit=" + Boolean.toString(explicit) + '\'' +
                ", id=" + id + '\'' +
                ", trackName='" + name + '\'' +
                ", uri=" + uri + '\'' +
                ", is_playable=" + Boolean.toString(is_playable) + '\'' +
                '}';
    }

    public ArrayList<Artist> getArtists() { return artists; }

    public int getDuration_ms() { return duration_ms; }

    public boolean isExplicit() { return explicit; }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getUri() { return uri; }

    public boolean isPlayable() { return is_playable; }
}
