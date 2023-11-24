package use_case.get_token;
import data_access.Authorization;
import data_access.Token;

public interface GetTokenInputBoundary {

    void execute(GetTokenInputData getTokenInputData, Authorization auth);
}
