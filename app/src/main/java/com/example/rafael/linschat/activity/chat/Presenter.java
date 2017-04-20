package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Mensagem;
import com.example.rafael.linschat.util.LibraryClass;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Presenter implements MVPChat.Presenter {
    private MVPChat.Model model;
    private MVPChat.View view;
    private ArrayList<Mensagem> mensagems;

    public Presenter() {
        this.model = new Model(this);
        this.mensagems = new ArrayList<>();
    }

    @Override
    public ArrayList<Mensagem> getMenssagens() {
        return mensagems;
    }

    @Override
    public void postMenssagem(String menssagem) {
        model.postMenssagem(new Mensagem(LibraryClass.getAuth().getCurrentUser().getEmail(), menssagem));
    }

    @Override
    public void clearCampo() {
        view.clearCampo();
    }

    @Override
    public void retrieveMenssagens() {
        model.retrieveMenssagens(mensagems);
    }

    @Override
    public void refreshMenssagens() {
        view.refreshMenssagens();
    }

    @Override
    public void imOnline(Boolean status){
        model.imOnline(status);
    }

    @Override
    public void retrieveUsersOnline() {
        model.retrieveUsersOnline();
    }

    @Override
    public void refreshUsersOnline(String usersOnline) {
        view.refreshUsersOnline(usersOnline);
    }

    @Override
    public void setView(MVPChat.View view) {
        this.view = view;
    }

    @Override
    public void setDialog(String message) {
        view.setDialog(message);
    }

    @Override
    public void closeDialog() {
        view.closeDialog();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }
}
