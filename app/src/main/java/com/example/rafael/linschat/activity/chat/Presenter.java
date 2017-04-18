package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Menssagem;
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
    private ArrayList<Menssagem> menssagems;

    public Presenter() {
        this.model = new Model(this);
        this.menssagems = new ArrayList<>();
    }

    @Override
    public void retrieveMenssagens() {
        model.retrieveMenssagens(menssagems);
    }

    @Override
    public ArrayList<Menssagem> getMenssagens() {
        return menssagems;
    }

    @Override
    public void postMenssagem(String menssagem) {
        model.postMenssagem(new Menssagem(LibraryClass.getAuth().getCurrentUser().getEmail(), menssagem));
    }

    @Override
    public void setView(MVPChat.View view) {
        this.view = view;
    }

    @Override
    public void refreshMenssagens() {
        view.refreshMenssagens();
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

    @Override
    public void clearCampo() {
        view.clearCampo();
    }
}
