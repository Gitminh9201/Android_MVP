package com.example.duclh.mvp.presenter;

import com.example.duclh.mvp.model.ModelLogin;
import com.example.duclh.mvp.model.ModelResponseToPresenter;
import com.example.duclh.mvp.view.ViewLoginListener;

/**
 * Created by duclh on 24/07/2017.
 */

public class PresenterLogin implements ModelResponseToPresenter {
    ModelLogin modelLogin;
    private ViewLoginListener callback;

    public PresenterLogin(ViewLoginListener callback) {
        this.callback = callback;
    }

    public void receivedHenderLogin(String username, String pass) {
        modelLogin = new ModelLogin(this);
        modelLogin.handerLogin(username, pass);
    }

    @Override
    public void onLoginSuccess() {
        callback.onLoginViewSuccess();
    }

    @Override
    public void onLoginFailed() {
        callback.onLoginViewFailed();
    }
}
