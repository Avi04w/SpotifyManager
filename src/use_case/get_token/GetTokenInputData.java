package use_case.get_token;
import data_access.Authorization;
import data_access.Token;

public class GetTokenInputData {
    final private String token;

    public GetTokenInputData (String token, Authorization auth) {
        this.token = token;
    }

    String getToken() { return token; }
}
