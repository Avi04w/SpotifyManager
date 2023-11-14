package entity;

public class User {
    private String country;
    private String displayName;
    private String email;
    private String profileURL;
    private String id;
    private String uri;

    public User(String country, String displayName, String email, String profileURL, String id, String uri){
        this.country = country;
        this.displayName = displayName;
        this.email = email;
        this.profileURL = profileURL;
        this.id = id;
        this.uri = uri;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private String country;
        private String displayName;
        private String email;
        private String profileURL;
        private String id;
        private String uri;
        UserBuilder(){
        }

        public UserBuilder setCountry(String country){
            this.country = country;
            return this;
        }
        public UserBuilder setDisplayName(String displayName){
            this.displayName = displayName;
            return this;
        }
        public UserBuilder setEmail(String email){
            this.email = email;
            return this;
        }
        public UserBuilder setProfileURL(String profileURL){
            this.profileURL = profileURL;
            return this;
        }
        public UserBuilder setID(String id){
            this.id = id;
            return this;
        }
        public UserBuilder setURi(String uri){
            this.uri = uri;
            return this;
        }
        public User build(){
            return new User(country, displayName, email, profileURL, id, uri);
        }
    }
    public String getCountry(){
        return country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }
}
