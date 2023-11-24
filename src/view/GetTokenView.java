package view;
import data_access.Authorization;
import interface_adapter.get_token.GetTokenController;
import interface_adapter.get_token.GetTokenState;
import interface_adapter.get_token.GetTokenViewModel;
import data_access.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetTokenView extends JPanel implements ActionListener, PropertyChangeListener {
    private final GetTokenViewModel getTokenViewModel;
    final JTextField tokenInputField = new JTextField(15);
    private final JLabel tokenErrorField = new JLabel();
    JButton enterToken;
    private final GetTokenController getTokenController;

    public GetTokenView(GetTokenViewModel getTokenViewModel, GetTokenController controller, Authorization auth) {

        this.getTokenController = controller;
        this.getTokenViewModel = getTokenViewModel;
        this.getTokenViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Get Token Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel tokenInfo = new LabelTextPanel(new JLabel("Token"), tokenInputField);

        JPanel buttons = new JPanel();
        enterToken = new JButton("Enter Token");
        buttons.add(enterToken);

        enterToken.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(enterToken)) {
                            GetTokenState currentState = getTokenViewModel.getState();

                            getTokenController.execute(currentState.getToken(), auth);
                        }
                    }
                }
        );

        tokenInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GetTokenState currentState = getTokenViewModel.getState();
                        currentState.setToken(tokenInputField.getText() + e.getKeyChar());
                        getTokenViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.add(title);
        this.add(tokenInfo);
        this.add(tokenErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt){
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetTokenState state = (GetTokenState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(GetTokenState state) {
        tokenInputField.setText(state.getToken());
    }

}
