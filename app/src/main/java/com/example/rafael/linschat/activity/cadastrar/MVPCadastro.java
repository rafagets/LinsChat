package com.example.rafael.linschat.activity.cadastrar;

import com.example.rafael.linschat.domain.Usuario;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public interface MVPCadastro {
    interface Model{
        void cadastrar(Usuario usuario);
    }

    interface Presenter{
        void cadastrar(String nome, String email, String senha);
        void setView(MVPCadastro.View view);
        void fechar();

        void setDialog(String message);

        void closeDialog();

        void showMessage(String message);
    }

    interface View{
        void fechar();
        void showMessage(String message);

        void setDialog(String message);

        void closeDialog();
    }
}
