package com.example.rafael.linschat.domain.domainDAO;

import com.example.rafael.linschat.domain.Menssagem;
import com.example.rafael.linschat.util.CallFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public class MenssagemDAO implements Controle.MenssagemCtrl {

    @Override
    public void post(Menssagem menssagem, final CallFirebase call) {
        String codigo = RAIZ.child(CHILD).push().getKey();
        RAIZ
            .child(CHILD)
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
        RAIZ.child(CHILD).addValueEventListener(new ValueEventListener() {
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
}
