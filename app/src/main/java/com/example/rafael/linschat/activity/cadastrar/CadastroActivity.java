package com.example.rafael.linschat.activity.cadastrar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafael.linschat.R;

public class CadastroActivity extends AppCompatActivity implements MVPCadastro.View {
    private static MVPCadastro.Presenter presenter;
    private EditText nome, email, senha;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        if (presenter == null)
            presenter = new Presenter();
        presenter.setView(this);

        initViews();
    }

    private void initViews() {
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
    }


    @Override
    public void fechar() {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    public void cadastrarUsuario(View view) {
        presenter.cadastrar(
                nome.getText().toString(),
                email.getText().toString(),
                senha.getText().toString()
        );
    }
}
