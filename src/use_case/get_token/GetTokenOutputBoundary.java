package use_case.get_token;

public interface GetTokenOutputBoundary {
    void prepareSuccessView(GetTokenOutputData user);

    void prepareFailView(String error);
}