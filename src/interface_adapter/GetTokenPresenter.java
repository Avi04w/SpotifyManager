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

    public void execute(String token, Authorization auth) {
        GetTokenInputData getTokenInputData = new GetTokenInputData(token, auth);
        getTokenDAO.execute(getTokenInputData);
    }

    public void prepareSuccessView(Authorization token) {
        PlayerView playerView = new PlayerView(token);
        playerView.setVisible(true);
        viewInterface.success();
    }

    public void prepareFailView() {
        viewInterface.failure();
    }
}
