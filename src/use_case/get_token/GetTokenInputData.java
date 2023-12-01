package use_case.get_token;
import data_access.Authorization;
import data_access.Token;

public class GetTokenInputData {
    final private String token;
    final private Authorization auth;

    public GetTokenInputData (String token, Authorization auth) {
        this.token = token;
        this.auth = auth;
    }

    public String getToken() { return token; }

    public Authorization getAuth() { return auth; }
}
