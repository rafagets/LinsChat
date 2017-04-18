package com.example.rafael.linschat.activity.cadastrar;

import com.example.rafael.linschat.domain.Usuario;
import com.example.rafael.linschat.domain.domainDAO.Controle;
import com.example.rafael.linschat.domain.domainDAO.UsuarioDAO;
import com.example.rafael.linschat.util.CallFirebase;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Model implements MVPCadastro.Model {
    private MVPCadastro.Presenter presenter;
    private Controle.UsuarioCtrl usuarioCtrl;

    public Model(MVPCadastro.Presenter presenter) {
        this.presenter = presenter;
        this.usuarioCtrl = new UsuarioDAO();
    }

    @Override
    public void cadastrar(Usuario usuario) {
        presenter.setDialog("Cadastrando...");
        usuarioCtrl.cadastrar(usuario, new CallFirebase() {
            @Override
            public void result(boolean b) {
                presenter.closeDialog();
                if (b) {
                    presenter.showMessage("Cadastrado com sucesso!");
                    presenter.fechar();
                } else {
                    presenter.showMessage("Erro ao cadastrar");
                }
            }
        });
    }
}
