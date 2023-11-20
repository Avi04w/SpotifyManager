package api;

import entity.Track;

public interface TrackDB {
    Track getTrack(Authorization authorization, String id);
}
