package interface_adapter.get_token;
import data_access.Authorization;
import use_case.get_token.GetTokenInputBoundary;
import use_case.get_token.GetTokenInputData;

public class GetTokenController {

    final GetTokenInputBoundary getTokenUseCaseInteractor;
    public GetTokenController(GetTokenInputBoundary getTokenUseCaseInteractor) {
        this.getTokenUseCaseInteractor = getTokenUseCaseInteractor;
    }

    public void execute(String token, Authorization auth) {
        GetTokenInputData getTokenInputData = new GetTokenInputData(token, auth);

        getTokenUseCaseInteractor.execute(getTokenInputData, auth);
    }
}
