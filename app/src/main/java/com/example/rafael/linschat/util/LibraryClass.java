package com.example.rafael.linschat.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public final class LibraryClass {
    private static FirebaseAuth auth;
    private static DatabaseReference raiz;

    public static FirebaseAuth getAuth(){
        if (auth == null)
            auth = FirebaseAuth.getInstance();
        return auth;
    }

    public static DatabaseReference getRaiz(){
        if (raiz == null)
            raiz = FirebaseDatabase.getInstance().getReference();
        return raiz;
    }
}
