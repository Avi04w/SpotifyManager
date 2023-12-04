package interface_adapter;

import data_access.Authorization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerViewModel {
    private PlayerState playerState = new PlayerState();

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.playerState);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public PlayerState getPlayerState() {return playerState;}

}
