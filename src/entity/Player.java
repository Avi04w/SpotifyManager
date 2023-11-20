package entity;

import java.util.ArrayList;

public class Player {
    private final boolean isPlaying;
    private final Track track;
    private final int progress;

    public Player(boolean isPlaying, Track track, int progress) {
        this.isPlaying = isPlaying;
        this.track = track;
        this.progress = progress;
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {
        private boolean isPlaying;
        private Track track;
        private int progress;

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

        public Player build() {
            return new Player(isPlaying, track, progress);
        }
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
                "track='" + track +
                ", isPlaying=" + isPlaying +
                ", progress=" + progress +
                '}';
    }
}
