package entity;

import java.util.Arrays;

public class Artist {

    // Refer to the API documentation for the meaning of these fields.
    private String name;
    private String id;
    private String[] genres;
    private int popularity;
    private String uri;

    public Artist(String name, String id, int popularity, String uri) {
        this.name = name;
        this.id = id;
        this.popularity = popularity;
        this.uri = uri;
    }

    public static ArtistBuilder builder() {
        return new ArtistBuilder();
    }

    public static class ArtistBuilder {
        private String name;
        private String id;
//        private String[] genres;
        private int popularity;
        private String uri;

        ArtistBuilder() {
        }

        public ArtistBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ArtistBuilder id(String id) {
            this.id = id;
            return this;
        }

//        public ArtistBuilder genres(String[] genres) {
//            this.genres = genres;
//            return this;
//        }

        public ArtistBuilder popularity(int popularity){
            this.popularity = popularity;
            return this;
        }

        public ArtistBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Artist build() {
            return new Artist(name, id, popularity, uri);
        }
    }

    @Override
    public String toString() {
        return "Artist{" +
                "displayName='" + name + '\'' +
                ", id='" + id + '\'' +
//                ", genres='" + Arrays.toString(genres) + '\'' +
                ", popularity=" + Integer.toString(popularity) + '\'' +
                ", uri=" + uri +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

//    public String[] getGenres() {
//        return genres;
//    }

    public int getPopularity() {
        return popularity;
    }

    public String getUri() {
        return uri;
    }
}
