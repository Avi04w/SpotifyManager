package use_case.player;

public interface PlayerOutputBoundary {
    void prepareResumeSuccessView(PlayerOutputData player);
    void preparePauseSuccessView();
    void prepareFailView();
}
