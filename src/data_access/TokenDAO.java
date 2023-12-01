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
    public boolean execute(GetTokenInputData getTokenInputData) {
        String token = getTokenInputData.getToken();
        String result = getTokenInputData.getAuth().setAccessAndRefreshToken(token);

        // If there is an error this will return false so that the GUI will know to send the user an error message.
        // Otherwise, this tells the presenter to switch to the Player GUI.
        if (result.contains("Error: ")){
            return false;
        } else {
            getTokenPresenter.prepareSuccessView();
            return true;
        }
    }
}
