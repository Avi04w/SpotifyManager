package app;

import api.Authorization;
import api.Token;
import entity.Artist;
import entity.Player;
import use_case.GetArtistUseCase;
import use_case.GetPlayerUseCase;

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

        GetArtistUseCase getArtistUseCase = config.getArtistUseCase(token, "0TnOYISbd1XYRBk9myaseg");
//        GetPlayerUseCase getPlayerUseCase = config.getPlayerUseCase(authorization);

        Artist artist = getArtistUseCase.getArtist();

        System.out.println(artist);

//        Player player = getPlayerUseCase.getPlayer();
    }
}
