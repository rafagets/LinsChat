package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Mensagem;
import com.example.rafael.linschat.DAO.Controle;
import com.example.rafael.linschat.DAO.MensagemDAO;
import com.example.rafael.linschat.util.CallFirebase;
import com.example.rafael.linschat.util.LibraryClass;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Model implements MVPChat.Model {
    private MVPChat.Presenter presenter;
    private Controle.MensagemCtrl mensagemCtrl;

    public Model(MVPChat.Presenter presenter) {
        this.presenter = presenter;
        this.mensagemCtrl = new MensagemDAO();
    }

    @Override
    public void postMenssagem(Mensagem mensagem) {
        mensagemCtrl.post(mensagem, new CallFirebase() {
            @Override
            public void result(boolean b) {
                if (b)
                    presenter.clearCampo();
                else
                    presenter.showMessage("error");
            }
        });
    }

    @Override
    public void retrieveMenssagens(ArrayList<Mensagem> mensagems) {
        presenter.setDialog("Buscando chat...");
        mensagemCtrl.retrieveList(mensagems, new CallFirebase() {
            @Override
            public void result(boolean b) {
                presenter.closeDialog();
                if (b)
                    presenter.refreshMenssagens();
            }
        });
    }

    @Override
    public void imOnline(Boolean status) {
        if (status) {
            HashMap<String, Object> user = new HashMap<>();
            String nome = LibraryClass.getAuth().getCurrentUser().getDisplayName();
            String uId = LibraryClass.getAuth().getCurrentUser().getUid();
            user.put(uId, nome);
            mensagemCtrl.imOnline(user);
        }else{
            mensagemCtrl.imOffline(LibraryClass.getAuth().getCurrentUser().getUid());
        }
    }

    @Override
    public void retrieveUsersOnline(){
        final HashMap<String, String> users = new HashMap<>();
        mensagemCtrl.retrieveUsersOnline(users, new CallFirebase() {
            @Override
            public void result(boolean b) {
                if (b)
                    prepareUsers(users);
            }
        });
    }


    private void prepareUsers(HashMap<String, String> users) {
        int qtd = 0;
        String usuarios = "";
        if (users.size() > 0) {
            for (String key : users.values()) {
                usuarios += key + ", ";
                qtd++;
            }

            usuarios = usuarios.substring(0, usuarios.length() - 2);
        }
        String usersOnline = String.format("(%s) %s", String.valueOf(qtd), usuarios);
        presenter.refreshUsersOnline(usersOnline);
    }
}