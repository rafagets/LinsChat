package com.example.rafael.linschat.activity.main;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public interface MVPMain {
    interface Model {
        void login(String email, String senha);
    }

    interface Presenter {
        void login(String email, String senha);
        void setView(MVPMain.View view);
        void setDialog(String message);

        void closeDialog();

        void showMessage(String message);
        void startChat();
    }

    interface View {
        void setDialog(String message);
        void closeDialog();
        void showMessage(String message);
        void startChat();
    }
}
