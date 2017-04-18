package com.example.rafael.linschat.activity.main;

import com.example.rafael.linschat.domain.domainDAO.Controle;
import com.example.rafael.linschat.domain.domainDAO.UsuarioDAO;
import com.example.rafael.linschat.util.CallFirebase;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Model implements MVPMain.Model {
    private MVPMain.Presenter presenter;
    private Controle.UsuarioCtrl usuarioCtrl;

    public Model(MVPMain.Presenter presenter) {
        this.presenter = presenter;
        this.usuarioCtrl = new UsuarioDAO();
    }

    @Override
    public void login(String email, String senha) {
        presenter.setDialog("Autenticando...");
        usuarioCtrl.entrar(email, senha, new CallFirebase() {
            @Override
            public void result(boolean b) {
                presenter.closeDialog();
                if (b){
                    presenter.showMessage("Bem vindo!");
                    presenter.startChat();
                }else {
                    presenter.showMessage("Dados incorretos.");
                }
            }
        });
    }
}
