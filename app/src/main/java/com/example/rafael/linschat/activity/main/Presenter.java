package com.example.rafael.linschat.activity.main;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class Presenter implements MVPMain.Presenter {
    private MVPMain.Model model;
    private MVPMain.View view;

    public Presenter() {
        this.model = new Model(this);
    }

    @Override
    public void login(String email, String senha) {
        model.login(email, senha);
    }

    @Override
    public void setView(MVPMain.View view) {
        this.view = view;
    }

    @Override
    public void setDialog(String message) {
        view.setDialog(message);
    }

    @Override
    public void closeDialog(){
        view.closeDialog();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void startChat() {
        view.startChat();
    }
}
