package interface_adapter.get_token;

public class GetTokenState {
    private String token = "";
    private String tokenError = null;

    public GetTokenState(GetTokenState copy) {
        token = copy.token;
        tokenError = copy.tokenError;
    }

    public GetTokenState() {}

    public String getToken() {
        return token;
    }

    public String getTokenError() {
        return tokenError;
    }

    public void setToken(String token) { this.token = token; }

    public void setTokenError(String usernameError) {
        this.tokenError = tokenError;
    }

}
