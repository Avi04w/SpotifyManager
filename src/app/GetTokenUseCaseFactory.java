package app;

import data_access.Authorization;
import data_access.Token;
import interface_adapter.get_token.GetTokenController;
import interface_adapter.get_token.GetTokenPresenter;
import interface_adapter.get_token.GetTokenViewModel;
import interface_adapter.ViewManagerModel;
import use_case.get_token.GetTokenInputBoundary;
import use_case.get_token.GetTokenInteractor;
import use_case.get_token.GetTokenOutputBoundary;
import use_case.get_token.GetTokenDataAccessInterface;
import view.GetTokenView;

import javax.swing.*;
import java.io.IOException;

public class GetTokenUseCaseFactory {
    private GetTokenUseCaseFactory() {}

    public static GetTokenView create(ViewManagerModel viewManagerModel,
                                      GetTokenViewModel getTokenViewModel,
                                      GetTokenDataAccessInterface userDataAccessObject) {
        Authorization auth = new Token();
        try {
            GetTokenController getTokenController = createGetTokenUseCase(viewManagerModel, getTokenViewModel, userDataAccessObject);
            return new GetTokenView(getTokenViewModel, getTokenController, auth);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open data file.");
        }

        return null;
    }

    private static GetTokenController createGetTokenUseCase(
            ViewManagerModel viewManagerModel,
            GetTokenViewModel getTokenViewModel,
            GetTokenDataAccessInterface userDataAccessObject) throws IOException {

        GetTokenOutputBoundary getTokenOutputBoundary = new GetTokenPresenter(getTokenViewModel, viewManagerModel);

        GetTokenInputBoundary getTokenInteractor = new GetTokenInteractor(userDataAccessObject, getTokenOutputBoundary);

        return new GetTokenController(getTokenInteractor);
    }
}
