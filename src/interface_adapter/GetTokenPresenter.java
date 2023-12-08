package interface_adapter;

import use_case.get_token.GetTokenDataAccessInterface;
import use_case.get_token.GetTokenInputData;
import use_case.get_token.GetTokenOutputBoundary;
import view.PlayerView;
import data_access.Authorization;

public class GetTokenPresenter implements GetTokenOutputBoundary {

    private GetTokenDataAccessInterface getTokenDAO;
    private final GetTokenViewInterface viewInterface;

    public GetTokenPresenter(GetTokenViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public void setDAO(GetTokenDataAccessInterface getTokenDAO) {
        this.getTokenDAO = getTokenDAO;
    }

    /**
     * Executes the process of obtaining and processing a token using the provided token and Authorization object.
     * Creates a GetTokenInputData instance with the given token and Authorization object,
     * then delegates the execution to the associated GetTokenDAO.
     *
     * @param token The token to be processed.
     * @param auth  The Authorization object associated with the token.
     */
    public void execute(String token, Authorization auth) {
        GetTokenInputData getTokenInputData = new GetTokenInputData(token, auth);
        getTokenDAO.execute(getTokenInputData);
    }

    /**
     * Prepares the view for success by creating a PlayerView with the provided Authorization token,
     * making it visible, and notifying the associated view interface about the success.
     *
     * @param token The Authorization token representing the user's authentication status.
     */
    public void prepareSuccessView(Authorization token) {
        PlayerView playerView = new PlayerView(token);
        playerView.setVisible(true);
        viewInterface.success();
    }

    /**
     * Prepares the view for failure by notifying the associated view interface about the failure.
     * This method is typically called when there is an error during the token retrieval process.
     */
    public void prepareFailView() {
        viewInterface.failure();
    }
}
