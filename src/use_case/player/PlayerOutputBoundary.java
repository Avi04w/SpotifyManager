package use_case.player;

public interface PlayerOutputBoundary {
    void prepareResumeSuccessView();
    void preparePauseSuccessView();
    void prepareFailView();
}
