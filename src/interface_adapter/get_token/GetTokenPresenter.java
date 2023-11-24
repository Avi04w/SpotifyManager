package interface_adapter.get_token;

import use_case.get_token.GetTokenOutputBoundary;
import use_case.get_token.GetTokenOutputData;
import interface_adapter.ViewManagerModel;

public class GetTokenPresenter implements GetTokenOutputBoundary {
    private final GetTokenViewModel getTokenViewModel;
    private ViewManagerModel viewManagerModel;

    public GetTokenPresenter(GetTokenViewModel getTokenViewModel,
                             ViewManagerModel viewManagerModel){
        this.getTokenViewModel = getTokenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetTokenOutputData response) {
        this.viewManagerModel.setActiveView("Login");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        GetTokenState getTokenState = getTokenViewModel.getState();
        getTokenState.setTokenError(error);
        getTokenViewModel.firePropertyChanged();
    }
}
