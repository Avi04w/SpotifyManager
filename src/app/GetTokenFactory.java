package app;

import data_access.TokenDAO;
import interface_adapter.GetTokenPresenter;
import view.GetTokenView;

public class GetTokenFactory {
    public GetTokenPresenter createPresenter(GetTokenView view) {
        GetTokenPresenter presenter = new GetTokenPresenter(view);
        TokenDAO tokenDAO = new TokenDAO(presenter);
        presenter.setDAO(tokenDAO);
        return presenter;
    }
}
