package use_case.player;

import data_access.Authorization;

public interface PlayerOutputBoundary {
    void prepareResumeSuccessView(PlayerOutputData player, Authorization token);
    void preparePauseSuccessView();
    void prepareFailView();
}
