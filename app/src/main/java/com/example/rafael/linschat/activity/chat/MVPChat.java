package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Mensagem;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public interface MVPChat {
    interface Model {
        void postMenssagem(Mensagem mensagem);
        void retrieveMenssagens(ArrayList<Mensagem> mensagems);

        void imOnline(Boolean status);
        void retrieveUsersOnline();
    }

    interface Presenter {
        ArrayList<Mensagem> getMenssagens();
        void postMenssagem(String menssagem);
        void retrieveMenssagens();
        void refreshMenssagens();
        void clearCampo();

        void imOnline(Boolean status);
        void retrieveUsersOnline();
        void refreshUsersOnline(String usersOnline);

        void setView(MVPChat.View view);
        void setDialog(String message);
        void closeDialog();
        void showMessage(String message);
    }

    interface View {
        void refreshMenssagens();
        void clearCampo();
        void refreshUsersOnline(String usersOnline);

        void setDialog(String message);
        void closeDialog();
        void showMessage(String message);
    }
}
