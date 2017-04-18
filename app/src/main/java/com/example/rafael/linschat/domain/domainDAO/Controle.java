package com.example.rafael.linschat.domain.domainDAO;

import com.example.rafael.linschat.domain.Menssagem;
import com.example.rafael.linschat.domain.Usuario;
import com.example.rafael.linschat.util.CallFirebase;
import com.example.rafael.linschat.util.LibraryClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public interface Controle {
    interface UsuarioCtrl{
        FirebaseAuth AUTH = LibraryClass.getAuth();
        DatabaseReference RAIZ = LibraryClass.getRaiz();
        String CHILD = "usuarios";

        void entrar(String email, String senha, CallFirebase call);
        void cadastrar(Usuario usuario, CallFirebase call);
    }

    interface MenssagemCtrl{
        DatabaseReference RAIZ = LibraryClass.getRaiz();
        String CHILD = "menssagens";

        void post(Menssagem menssagem, CallFirebase call);
        void retrieveList(ArrayList<Menssagem> menssagems, CallFirebase call);
    }
}
