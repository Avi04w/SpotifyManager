package data_access;

import use_case.get_token.GetTokenDataAccessInterface;
import use_case.get_token.GetTokenInputData;
import use_case.get_token.GetTokenOutputBoundary;

public class TokenDAO implements GetTokenDataAccessInterface {

    private final GetTokenOutputBoundary getTokenPresenter;

    public TokenDAO(GetTokenOutputBoundary getTokenOutputBoundary) {
        this.getTokenPresenter = getTokenOutputBoundary;
    }

    @Override
    public void execute(GetTokenInputData getTokenInputData) {
        String token = getTokenInputData.getToken();
        String result = getTokenInputData.getAuth().setAccessAndRefreshToken(token);

        // If there is an error this will tell the presenter send the user an error message.
        // Otherwise, this tells the presenter to switch to the Player View.
        if (result.contains("Error: ")){
            getTokenPresenter.prepareFailView();
        } else {
            getTokenPresenter.prepareSuccessView(getTokenInputData.getAuth());
        }
    }
}
