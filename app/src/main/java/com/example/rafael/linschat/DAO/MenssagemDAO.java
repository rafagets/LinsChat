package com.example.rafael.linschat.DAO;

import com.example.rafael.linschat.domain.Menssagem;
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

public class MenssagemDAO implements Controle.MenssagemCtrl {

    @Override
    public void post(Menssagem menssagem, final CallFirebase call) {
        String codigo = RAIZ.push().getKey();
        RAIZ
            .child("menssagens")
            .child(codigo)
            .setValue(menssagem, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    call.result(true);
                }
            });
    }

    @Override
    public void retrieveList(final ArrayList<Menssagem> menssagems, final CallFirebase call) {
        RAIZ.child("menssagens").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menssagems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    menssagems.add(snapshot.getValue(Menssagem.class));
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
