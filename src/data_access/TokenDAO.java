package data_access;

import use_case.get_token.GetTokenDataAccessInterface;
import use_case.get_token.GetTokenInputData;
import use_case.get_token.GetTokenOutputBoundary;

public class TokenDAO implements GetTokenDataAccessInterface {

    private final GetTokenOutputBoundary getTokenPresenter;

    public TokenDAO(GetTokenOutputBoundary getTokenOutputBoundary) {
        this.getTokenPresenter = getTokenOutputBoundary;
    }

    /**
     * Executes the token retrieval process based on the provided GetTokenInputData.
     * Retrieves the access token from the input data, sets it in the associated Authorization object,
     * and communicates the result to the presenter for further handling.
     *
     * @param getTokenInputData The input data containing the access token and associated Authorization object.
     * @see GetTokenInputData
     */
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
