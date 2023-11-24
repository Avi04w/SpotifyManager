package interface_adapter.get_token;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetTokenViewModel extends ViewModel {
    private GetTokenState state = new GetTokenState();

    public GetTokenViewModel() { super("log in"); }

    public void setState(GetTokenState getTokenState) { this.state = getTokenState; };

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() { support.firePropertyChange("getTokenState", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public GetTokenState getState() {
        return state;
    }
}
