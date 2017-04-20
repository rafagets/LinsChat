package com.example.rafael.linschat.DAO;

import android.support.annotation.NonNull;

import com.example.rafael.linschat.domain.Usuario;
import com.example.rafael.linschat.util.CallFirebase;
import com.example.rafael.linschat.util.LibraryClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 17/04/2017.
 * Todos os direitos reservados.
 */

public class UsuarioDAO implements Controle.UsuarioCtrl {
    @Override
    public void entrar(String email, String senha, final CallFirebase call) {
        //verifica se o usuario esta conectado, caso sim, encerra a conexao:
        if (AUTH.getCurrentUser() != null)
            AUTH.signOut();

        // faz login usando email e senha
        AUTH.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            call.result(true);
                        else
                            call.result(false);
                    }
                });
    }

    @Override
    public void cadastrar(final Usuario usuario, final CallFirebase call) {
        AUTH.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LibraryClass.getAuth().getCurrentUser().updateProfile(
                                    new UserProfileChangeRequest
                                            .Builder()
                                            .setDisplayName(usuario.getNome())
                                    .build()
                            );
                            completeCadastro(usuario, call);
                        }else
                            call.result(false);
                    }
                });
    }

    /**
     * Logo após a criação de um novo usuario, o firebase reserva um uId exclusivo para o usuario.
     * por padrao, o firebase cria um banco aparte onde ele salva o email, uid e senha do usuario, onde que somente o firebase tem acesso.
     * Então, precisamos persistir esse uId, junto com suas demais informações como nome e email em nossa base de dados.
     */
    private void completeCadastro(Usuario usuario, final CallFirebase call){
        // limpamos a senha do usuario para que a mesma não seja salva no nosso banco.
        usuario.setSenha(null);
        // salvamos o uId do usuario em nosso banco de dados
        usuario.setuId(AUTH.getCurrentUser().getUid());

        // salva em usuarios/codigo/
        RAIZ
            .child(AUTH.getCurrentUser().getUid())
            .setValue(usuario, new DatabaseReference.CompletionListener() {
            @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    call.result(true);
                }
            });
    }
}
