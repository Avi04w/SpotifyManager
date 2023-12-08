package entity;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private final String name;
    private final String id;
    private final String uri;
    private final ArrayList<Artist> artists;
    private final String type;
    private final String image;
    private final int totalTracks;
    private final ArrayList<String> genres;
    private final int popularity;

    /**
     * Initializes an instance of Album.
     * This method initializes an instance of Album to construct and ALbum object containing information such as the
     * Album name, Id, URI, artists, image, and more.
     *
     * @param name The Name of the Album.
     * @param id The Spotify ID of the Album.
     * @param uri The Spotify URI of the Album.
     * @param artists An ArrayList of the Artists who are involved in the Album.
     * @param type The type of the Album.
     * @param image The URL link of the image cover art of the Album.
     * @param totalTracks The number of tracks contained in the Album.
     * @param genres An ArrayList of genres associated with the Album.
     * @param popularity The number of popularity of the Album, range between 0 to 100.
     */

    public Album(String name, String id, String uri, ArrayList<Artist> artists, String type, String image, int totalTracks, ArrayList<String> genres, int popularity) {
        this.name = name;
        this.id = id;
        this.uri = uri;
        this.artists = artists;
        this.type = type;
        this.image = image;
        this.totalTracks = totalTracks;
        this.genres = genres;
        this.popularity = popularity;
    }

    /**
     * This method builds a new Album object.
     * @return A new Album Object
     */
    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    /**
     * This method builds a new Album object tha adheres to the builder design pattern.
     */
    public static class AlbumBuilder {
        private String name;
        private String id;
        private String uri;
        private ArrayList<Artist> artists;
        private String type;
        private String image;
        private int totalTracks;
        private ArrayList<String> genres;
        private int popularity;
        AlbumBuilder(){
        }

        /**
         * Sets the name for the new instance of Album.
         * It can be an arbitrary string with more than one character that is not blank.
         * @param name The name of the Album.
         * @return The name of the Album.
         */
        public AlbumBuilder name(String name){
            this.name = name;
            return this;
        }

        /**
         * Sets the Spotify ID for the new instance of Album.
         * It can be an arbitrary string with more than one character that is not blank that adheres to Spotify.
         * @param id The Spotify ID of the Album.
         * @return The Spotify ID of the Album.
         */
        public AlbumBuilder id(String id){
            this.id = id;
            return this;
        }

        /**
         * Sets the Spotify URI for the new instance of Album.
         * It can be an arbitrary string with more than one character that is not blank.
         * @param uri The Spotify URI of the Album.
         * @return The Spotify URI of the Album.
         */
        public AlbumBuilder uri(String uri){
            this.uri = uri;
            return this;
        }

        /**
         * Sets an ArrayList of Artists for the new instance of Album.
         * @param artists The list of Artists involved in singing Tracks contained in the Album.
         * @return The list of Artists involved in singing Tracks contained in the Album.
         */
        public AlbumBuilder artists (ArrayList<Artist> artists){
            this.artists = artists;
            return this;
        }

        /**
         * Sets the Album type for the new instance of Album.
         * It can be an arbitrary string with more than one character that is not blank.
         * @param type The type of the Album.
         * @return The type of the Album.
         */
        public AlbumBuilder type (String type){
            this.type = type;
            return this;
        }

        /**
         * Sets the URL Link for the image cover for the new instance of Album.
         * It can be an arbitrary string with more than one character that is not blank.
         * @param image The image cover art of the Album.
         * @return The image cover art of the Album.
         */
        public AlbumBuilder image (String image){
            this.image = image;
            return this;
        }

        /**
         * Sets the total number of tracks in the Album for the new instance of Album.
         * @param totalTracks The total number of tracks contained in the Album.
         * @return The total number of tracks contained in the Album.
         */
        public AlbumBuilder totalTracks(int totalTracks){
            this.totalTracks = totalTracks;
            return this;
        }

        /**
         * Sets an ArrayList of genres of the Album for the new instance of Album.
         * @param genres The list of genres associated with the Tracks contained in the Album.
         * @return The list of genres associated with the Tracks contained in the Album.
         */
        public AlbumBuilder genres(ArrayList<String> genres){
            this.genres = genres;
            return this;
        }

        /**
         * Sets the number of popularity for the new instance of Album.
         * It can be an integer ranging from 0 to 100 with 100 being the most popular.
         * @param popularity The number of popularity for the Album.
         * @return The number of popularity for the Album.
         */
        public AlbumBuilder popularity(int popularity){
            this.popularity = popularity;
            return this;
        }

        /**
         * This method builds an Album object inside the AlbumBuilder class to allow usage and initialization of the Album outside the class.
         * @return A new instance of Album with attributes of name, id, uri, artists, type, image, totalTracks, genres and popularity already set.
         */
        public Album build() {
            return new Album(name, id, uri, artists, type, image, totalTracks, genres, popularity);
        }
    }

    /**
     * Retrieves the name of the Album object.
     * This method gets the name of the Album object to allow passing the information outside of the class.
     * @return The name of the Album.
     */
    public String getAlbumName() { return name; }

    /**
     * Retrieves the Spotify ID of the Album object.
     * This method gets the Spotify ID of the Album object to allow passing the information outside of the class.
     * @return The Spotify ID of the Album.
     */
    public String getId() { return id; }

    /**
     * Retrieves the Spotify URI of the Album object.
     * This method gets the Spotify URI of the Album object to allow passing the information outside of the class.
     * @return The Spotify URI of the Album.
     */
    public String getUri() { return uri; }

    /**
     * Retrieves an ArrayList of Artists of the Album object.
     * This method gets an ArrayList of Artists of the Album object to allow passing the information outside of the class.
     * @return An ArrayList of Artists of the Album object.
     */
    public ArrayList<Artist> getArtists() { return artists; }

    /**
     * Retrieves the type of the Album object.
     * This method gets the type of the Album object to allow passing the information outside of the class.
     * @return The type of the Album.
     */
    public String getType() { return type; }

    /**
     * Retrieves the image cover of the Album object.
     * This method gets the image cover of the Album object to allow passing the information outside of the class.
     * @return The image cover of the Album object.
     */
    public String getImage() { return image; }

    /**
     * Retrieves the total number of Tracks of the Album object.
     * This method gets the total number of Tracks of the Album object to allow passing the information outside of the class.
     * @return The total number of Tracks of the Album object.
     */
    public int getTotalTracks() { return totalTracks; }

    /**
     * Retrieves an ArrayList of genres associated with the Album object.
     * This method gets an ArrayList of genres of the Album object to allow passing the information outside of the class.
     * @return An ArrayList of genres of the Album object.
     */
    public ArrayList<String> getGenres() { return genres; }

    /**
     * Retrieves the number of popularity of the Album object.
     * This method gets the number of popularity of the Album object to allow passing the information outside of the class.
     * @return The number of popularity of the Album object.
     */
    public int getPopularity() { return popularity; }
}
