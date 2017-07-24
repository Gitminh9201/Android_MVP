package com.example.duclh.mvp.model;

/**
 * Created by duclh on 24/07/2017.
 */

public class ModelLogin {
    ModelResponseToPresenter callback;

    public ModelLogin(ModelResponseToPresenter callback) {
        this.callback = callback;
    }

    public void handerLogin(String username, String pass){
        if ("duclh".equals(username) && "123".equals(pass)) {
            callback.onLoginSuccess();
        } else {
            callback.onLoginFailed();
        }
    }
}
