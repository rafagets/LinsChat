package com.example.rafael.linschat.activity.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rafael.linschat.R;
import com.example.rafael.linschat.domain.Mensagem;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(View itemView) {
        super(itemView);
    }

    public void setDados(Mensagem mensagem) {
        TextView menssage, nome;
        menssage = (TextView) itemView.findViewById(R.id.mensagem);
        nome = (TextView) itemView.findViewById(R.id.nome);

        menssage.setText(mensagem.getMessage());
        nome.setText(mensagem.getNomeUsuario());
    }
}
