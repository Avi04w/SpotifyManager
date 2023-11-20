package use_case.track;

import data_access.Authorization;
import entity.Track;

public interface TrackDataAccessInterface {
    Track getTrack(Authorization authorization, String id);
}
