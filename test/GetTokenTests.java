import app.GetTokenFactory;
import data_access.Authorization;
import data_access.Token;
import org.junit.FixMethodOrder;
import interface_adapter.GetTokenPresenter;
import view.GetTokenView;
import org.junit.runners.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetTokenTests {
    //The order is fixed because if testGoodCode is successful and is tested before testBadCode, testBadCode can start with an invisible view which makes it fail, since it tests to make sure that the view is not made invisible by the bad code
    static String code = "AQB81yhPPBt3Hl5s-O4pGA7YfvE2gC_nHunt31ay4qH0WEczUJ5ak45esso8V152JXapABKQMXBDPRTPQzxhOSHFLy1mZ4s1emh16QlHNRe1UPHyn_-aaJcJij_U14TtgHg9or5wWLYXXJ6f87OvmsvxTgejNMuOvGJxsEMvvQKLaDzoPeTWdlBi5fJfLpNvFLXOwAzRw3imlvb1ipiMXP5GSAKG1gDUw5iuVgCOJ9vWfcYi3GdFBmTEtAiKT9I4LbIQiti5jpIEsJKges91s-RRMaiv1jsfJk6rXj9KqfcS6n30XwZUQ0SNHIv9-iEuqJHU0xJ5W9dMfpr-EvaV-RZl6UTU3n_8E4BQAOjqkgTiqQt4V7ORbNrA57SbVbXlwY80bQC_UmSVgtZ2U3bBkH4IeKPYggBSdHi16p9gzKMiexjmYpsmRQQj76V0XkQSMVMskNFd0URI";
    //CHANGE THIS EVERY TIME YOU RUN THIS TEST - GET NEW ONE BY RUNNING MAIN
    static Authorization token;
    static GetTokenView getTokenView;
    static GetTokenPresenter presenter;

    @org.junit.BeforeClass
    public static void setSpotifyApiObject(){
        token = new Token();
    }

    @org.junit.BeforeClass
    public static void setGetTokenClasses(){
        getTokenView = new GetTokenView(token);
        getTokenView.setVisible(true);
        GetTokenFactory factory = new GetTokenFactory();
        presenter = factory.createPresenter(getTokenView);
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