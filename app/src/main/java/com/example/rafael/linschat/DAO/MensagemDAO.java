package com.example.rafael.linschat.DAO;

import com.example.rafael.linschat.domain.Mensagem;
import com.example.rafael.linschat.util.CallFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public class MensagemDAO implements Controle.MensagemCtrl {

    @Override
    public void post(Mensagem mensagem, final CallFirebase call) {
        String codigo = RAIZ.push().getKey();
        RAIZ
            .child("posts")
            .child(codigo)
            .setValue(mensagem, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    call.result(true);
                }
            });
    }

    @Override
    public void retrieveList(final ArrayList<Mensagem> mensagems, final CallFirebase call) {
        RAIZ.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mensagems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    mensagems.add(snapshot.getValue(Mensagem.class));
                call.result(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                call.result(false);
            }
        });
    }

    @Override
    public void imOnline(HashMap<String, Object> user) {
        RAIZ
            .child("users")
            .updateChildren(user);
    }

    @Override
    public void imOffline(String uId){
        RAIZ
            .child("users")
            .child(uId)
            .removeValue();
    }

    @Override
    public void retrieveUsersOnline(final Map<String, String> users, final CallFirebase call){
        RAIZ.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    users.put(snapshot.getKey(), (String) snapshot.getValue());
                call.result(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                call.result(false);
            }
        });
    }
}
