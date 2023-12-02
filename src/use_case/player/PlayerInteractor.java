package use_case.player;

public class PlayerInteractor implements PlayerInputBoundary{
    final PlayerDataAccessInterface playerDAO;
    public PlayerInteractor(PlayerDataAccessInterface playerDataAccessInterface) {
        this.playerDAO = playerDataAccessInterface;
    }
    @Override
    public void resume(PlayerInputData playerInputData){
        playerDAO.resume(playerInputData.getAuthorization(), playerInputData.getDevice());
    }
    @Override
    public void pause(PlayerInputData playerInputData){
        playerDAO.pause(playerInputData.getAuthorization(), playerInputData.getDevice());
    }
}
