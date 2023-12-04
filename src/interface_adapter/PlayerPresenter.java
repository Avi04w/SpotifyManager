package interface_adapter;

import data_access.Authorization;
import use_case.player.PlayerOutputBoundary;
import use_case.player.PlayerOutputData;

public class PlayerPresenter implements PlayerOutputBoundary {
    private final PlayerViewModel playerViewModel;
    private final PlayerViewModel playerViewModel1;
    public PlayerPresenter(PlayerViewModel playerViewModel, PlayerViewModel playerViewModel1){

        this.playerViewModel = playerViewModel;
        this.playerViewModel1 = playerViewModel1;
    }

    @Override
    public void prepareResumeSuccessView(PlayerOutputData response, Authorization token) {
        PlayerState playerState = playerViewModel1.getPlayerState();
        playerState.setDeviceId(response.getAvailableDevice(token));
        playerState.setPlaying(true);
        this.playerViewModel1.setPlayerState(playerState);
        playerViewModel1.firePropertyChanged();
    }
    @Override
    public void preparePauseSuccessView(){
        PlayerState playerState = playerViewModel1.getPlayerState();
        playerState.setPlaying(false);
        this.playerViewModel1.setPlayerState(playerState);
        playerViewModel1.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        PlayerState playerState = playerViewModel.getPlayerState();
        //error?
        playerViewModel.firePropertyChanged();

    }
}
