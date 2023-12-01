package use_case.get_token;
import data_access.Authorization;

public interface GetTokenOutputBoundary {
    void prepareSuccessView();

    void prepareFailView();

    void execute(String token, Authorization auth);

}