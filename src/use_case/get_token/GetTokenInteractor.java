package use_case.get_token;
import data_access.Authorization;

public class GetTokenInteractor  implements GetTokenInputBoundary{
    final GetTokenDataAccessInterface dataAccessObject;
    final GetTokenOutputBoundary getTokenPresenter;

    public GetTokenInteractor(GetTokenDataAccessInterface dataAccessInterface,
                              GetTokenOutputBoundary getTokenOutputBoundary){
        this.dataAccessObject = dataAccessInterface;
        this.getTokenPresenter = getTokenOutputBoundary;
    }

    @Override
    public void execute(GetTokenInputData getTokenInputData, Authorization auth){
        String token = getTokenInputData.getToken();
        String result = auth.setAccessAndRefreshToken(token);

        if (result.contains("Error: ")){
            getTokenPresenter.prepareFailView(result);
        } else {
            GetTokenOutputData getTokenOutputData = new GetTokenOutputData(false);
            getTokenPresenter.prepareSuccessView(getTokenOutputData);
        }
    }
}
