package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Menssagem;
import com.example.rafael.linschat.domain.domainDAO.Controle;
import com.example.rafael.linschat.domain.domainDAO.MenssagemDAO;
import com.example.rafael.linschat.util.CallFirebase;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Model implements MVPChat.Model {
    private MVPChat.Presenter presenter;
    private Controle.MenssagemCtrl menssagemCtrl;

    public Model(MVPChat.Presenter presenter) {
        this.presenter = presenter;
        this.menssagemCtrl = new MenssagemDAO();
    }

    @Override
    public void retrieveMenssagens(ArrayList<Menssagem> menssagems) {
        presenter.setDialog("Buscando chat...");
        menssagemCtrl.retrieveList(menssagems, new CallFirebase() {
            @Override
            public void result(boolean b) {
                presenter.closeDialog();
                if (b)
                    presenter.refreshMenssagens();
            }
        });
    }

    @Override
    public void postMenssagem(Menssagem menssagem) {
        menssagemCtrl.post(menssagem, new CallFirebase() {
            @Override
            public void result(boolean b) {
                if (b)
                    presenter.clearCampo();
                else
                    presenter.showMessage("error");
            }
        });
    }
}
