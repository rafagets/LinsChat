package com.example.rafael.linschat.activity.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.linschat.R;
import com.example.rafael.linschat.activity.cadastrar.CadastroActivity;
import com.example.rafael.linschat.activity.chat.ChatActivity;

public class MainActivity extends AppCompatActivity implements MVPMain.View {
    private static MVPMain.Presenter presenter;
    private EditText email, senha;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (presenter == null)
            presenter = new Presenter();
        presenter.setView(this);

        initViews();
    }

    private void initViews() {
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void entrar(View view) {
        presenter.login(
                email.getText().toString(),
                senha.getText().toString()
        );
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    @Override
    public void setDialog(String message) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.show();
    }

    @Override
    public void closeDialog(){
        dialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startChat() {
        startActivity(new Intent(this, ChatActivity.class));
    }
}
