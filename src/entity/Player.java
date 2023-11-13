package entity;

import java.util.ArrayList;

public class Player {
    private String name;
    private boolean isPlaying;
    private Track track;
    private int progress;

    public Player(String name, boolean isPlaying, Track track, int progress) {
        this.name = name;
        this.isPlaying = isPlaying;
        this.track = track;
        this.progress = progress;
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {
        private String name;
        private boolean isPlaying;
        private Track track;
        private int progress;

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

        public PlayerBuilder track(Track track){
            this.track = track;
            return this;
        }

        public PlayerBuilder progress(int progress) {
            this.progress = progress;
            return this;
        }

        public Player build() {
            return new Player(name, isPlaying, track, progress);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return "Player{" +
                "trackName='" + name +
                ", isPlaying=" + isPlaying +
                ", progress=" + progress +
                '}';
    }
}
