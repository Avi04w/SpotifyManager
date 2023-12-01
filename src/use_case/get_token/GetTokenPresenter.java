package use_case.get_token;

import app.gui.SpotifyGetTokenGUI;
import app.gui.SpotifyPlayerGUI;
import data_access.Authorization;

public class GetTokenPresenter implements GetTokenOutputBoundary{

    private GetTokenDataAccessInterface getTokenDAO;

    public void setDAO(GetTokenDataAccessInterface getTokenDAO) {
        this.getTokenDAO = getTokenDAO;
    }

    public boolean execute(String token, Authorization auth) {
        GetTokenInputData getTokenInputData = new GetTokenInputData(token, auth);
        return getTokenDAO.execute(getTokenInputData);
    }

    public void prepareSuccessView() {
        SpotifyPlayerGUI playerGUI = new SpotifyPlayerGUI();
        playerGUI.setVisible(true);
    }
}
