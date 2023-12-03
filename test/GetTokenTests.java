import data_access.Authorization;
import data_access.Token;
import data_access.TokenDAO;
import org.junit.FixMethodOrder;
import use_case.get_token.GetTokenPresenter;
import view.GetTokenView;
import org.junit.runners.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetTokenTests {
    //The order is fixed because if testGoodCode is successful and is tested before testBadCode, testBadCode can start with an invisible view which makes it fail, since it tests to make sure that the view is not made invisible by the bad code
    static String code = "AQAmyCyqu6VHe4T5TRO1WPb9TM6C08BdrMP23YsG1cpfjx4ATAKzq28ANnxqQj0LZutL14namh-c0_R6WJhxfXQdZyT4S05TV8tPqtXCf-6UhCIG_2RXpzIYuYTnnRs8xSKf01NxyyY4Q8Tc3nRQFOOR-Juts6pw32vNmodiI65rAtOP9ejoCk3cKFn52rGRFdwgaUYc1Zoq-sRUv4Ubwi_XS90Mu0Q5jq8Scr8U2MgJsnqAW0-MMuUJNL5duEH6L2YYpZkvmJTMtJI2JHXleJ1qgbqnbZIVp29dDefOiLJ-EIpUOEC7mc8soJ7i-5YuNzFT4NG9Jk_kMXBGGPzE4Q1NWSbtOXoXopaucUoL4N7gKEjjPCdmcKMDMHUFDW4eoC2msnyOAu8biUJO7UWKKXQGRuHqFFjB_nHeMqQf2KRb4rDhmeN64MAr0HwwI42Fbnu-Dh_a6iIF";
    //CHANGE THIS EVERY TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token;
    static GetTokenView getTokenView;
    static GetTokenPresenter presenter;
    static TokenDAO tokenDAO;

    @org.junit.BeforeClass
    public static void setSpotifyApiObject(){
        token = new Token();
    }

    @org.junit.BeforeClass
    public static void setGetTokenClasses(){
        getTokenView = new GetTokenView(token);
        getTokenView.setVisible(true);
        presenter = new GetTokenPresenter(getTokenView);
        tokenDAO = new TokenDAO(presenter);
        presenter.setDAO(tokenDAO);
    }

    @org.junit.Test
    public void testViewSetup() {
        assert getTokenView.isFontSet();
        assert getTokenView.isBackgroundSet();
    }

    @org.junit.Test
    public void testBadCode() {
        presenter.execute("Wrong Code", token);
        assert getTokenView.isVisible();
    }

    @org.junit.Test
    public void testGoodCode() {
        presenter.execute(code, token);
        assert !getTokenView.isVisible();
    }
}