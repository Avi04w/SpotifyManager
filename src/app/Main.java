package app;

import data_access.Authorization;
import data_access.Token;
import entity.Player;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Authorization token = new Token();

        System.out.println(token.getAuthURI());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Code: ");
        String code = scanner.nextLine();

        token.setAccessAndRefreshToken(code);

        System.out.println(token.getSpotifyApi().getAccessToken());
        System.out.println(token.getSpotifyApi().getRefreshToken());

        Config config = new Config();

        PlayerInputData playerInputData = config.getPlayerInputData(token);
        PlayerOutputData playerOutputData = config.getPlayerOutputData(token);

        Player player = playerOutputData.getPlayer(token);

        System.out.println(player);
    }
}
