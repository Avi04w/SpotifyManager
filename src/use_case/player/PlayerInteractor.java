package use_case.player;


public class PlayerInteractor implements PlayerInputBoundary{
    final PlayerDataAccessInterface playerDAO;
    final PlayerOutputBoundary playerPresenter;
    public PlayerInteractor(PlayerDataAccessInterface playerDataAccessInterface, PlayerOutputBoundary playerOutputBoundary) {
        this.playerDAO = playerDataAccessInterface;
        this.playerPresenter = playerOutputBoundary;
    }
    @Override
    public void resume(PlayerInputData playerInputData){
        if (playerInputData.getDevice().isEmpty()) {
            playerInputData.setDevice(playerDAO.getAvailableDevice(playerInputData.getAuthorization()));
        }
        playerDAO.resume(playerInputData.getAuthorization(), playerInputData.getDevice());
        PlayerOutputData playerOutputData = new PlayerOutputData(playerInputData.getAuthorization(), playerDAO);
        playerOutputData.setDevice(playerInputData.getDevice());

        playerPresenter.prepareResumeSuccessView(playerOutputData);
    }
    @Override
    public void pause(PlayerInputData playerInputData){
        playerDAO.pause(playerInputData.getAuthorization(), playerInputData.getDevice());
        playerPresenter.preparePauseSuccessView();
    }
}
