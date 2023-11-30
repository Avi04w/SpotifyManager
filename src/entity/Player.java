package entity;

public class Player {
    private final boolean isPlaying;
    private final Track track;
    private final int progress;
    private final int volume;
    private boolean shuffle;

    public Player(boolean isPlaying, Track track, int progress, int volume, boolean shuffle) {
        this.isPlaying = isPlaying;
        this.track = track;
        this.progress = progress;
        this.volume = volume;
        this.shuffle = shuffle;
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {
        private boolean isPlaying;
        private Track track;
        private int progress;
        private int volume;
        private boolean shuffle;

        PlayerBuilder() {
        }

        public PlayerBuilder isPlaying(boolean isPlaying) {
            this.isPlaying = isPlaying;
            return this;
        }

        public PlayerBuilder track(Track track){
            this.track = track;
            return this;
        }

        public PlayerBuilder progress(int progress) {
            this.progress = progress;
            return this;
        }

        public PlayerBuilder volume(int volume){
            this.volume = volume;
            return this;
        }

        public PlayerBuilder shuffle(boolean shuffle){
            this.shuffle = shuffle;
            return this;
        }

        public Player build() {
            return new Player(isPlaying, track, progress, volume, shuffle);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getProgress() {
        return progress;
    }

    public Track getCurrentTrack(){
        return track;
    }

    public int getVolume(){
        return volume;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    @Override
    public String toString() {
        return "Player{" +
                "track='" + track +
                ", isPlaying=" + isPlaying +
                ", progress=" + progress +
                ", volume=" + volume +
                ", shuffle=" + shuffle +
                '}';
    }
}
