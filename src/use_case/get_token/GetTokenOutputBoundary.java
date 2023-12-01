package use_case.get_token;

import app.gui.SpotifyGetTokenGUI;
import app.gui.SpotifyLoginGUI;
import data_access.Authorization;

public interface GetTokenOutputBoundary {
    void prepareSuccessView();

    boolean execute(String token, Authorization auth);

}