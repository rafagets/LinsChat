package com.example.rafael.linschat.activity.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafael.linschat.R;
import com.example.rafael.linschat.domain.Mensagem;

import java.util.ArrayList;

/**
 * Jovem Soluções M.E.
 * Created by Rafael  on 18/04/2017.
 * Todos os direitos reservados.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<Mensagem> mensagems;

    public MyAdapter(ArrayList<Mensagem> mensagems) {
        this.mensagems = mensagems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_chat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setDados(mensagems.get(position));
    }

    @Override
    public int getItemCount() {
        return mensagems.size();
    }
}
