package app;

import data_access.Authorization;
import data_access.Token;
import entity.Player;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;
import view.LoginView;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
