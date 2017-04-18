package com.example.rafael.linschat.activity.chat;

import com.example.rafael.linschat.domain.Menssagem;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public interface MVPChat {
    interface Model {
        void retrieveMenssagens(ArrayList<Menssagem> menssagems);
        void postMenssagem(Menssagem menssagem);
    }

    interface Presenter {
        void retrieveMenssagens();
        ArrayList<Menssagem> getMenssagens();
        void postMenssagem(String menssagem);
        void setView(MVPChat.View view);
        void refreshMenssagens();

        void setDialog(String message);

        void closeDialog();

        void showMessage(String message);
        void clearCampo();
    }

    interface View {
        void refreshMenssagens();
        void clearCampo();

        void setDialog(String message);

        void closeDialog();

        void showMessage(String message);
    }
}
