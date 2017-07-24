package com.example.duclh.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duclh.mvp.presenter.PresenterLogin;
import com.example.duclh.mvp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewLoginListener {
    private EditText edt_username;
    private EditText edt_pass;
    private Button btn_login;

    private PresenterLogin presenterLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username = (EditText) findViewById(R.id.edtUserName);
        edt_pass = (EditText) findViewById(R.id.edtPass);
        btn_login = (Button) findViewById(R.id.btnLogin);

        presenterLogin = new PresenterLogin(this);

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String username = edt_username.getText().toString();
                String pass = edt_pass.getText().toString();
                presenterLogin.receivedHenderLogin(username,pass);
                break;
        }
    }

    @Override
    public void onLoginViewSuccess() {
        Toast.makeText(getApplication(), "login Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginViewFailed() {
        Toast.makeText(getApplication(), "login fail", Toast.LENGTH_SHORT).show();
    }
}
