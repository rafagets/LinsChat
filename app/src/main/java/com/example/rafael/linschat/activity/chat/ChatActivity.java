package com.example.rafael.linschat.activity.chat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafael.linschat.R;
import com.example.rafael.linschat.activity.chat.adapter.MyAdapter;

public class ChatActivity extends AppCompatActivity implements MVPChat.View {
    private static MVPChat.Presenter presenter;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (presenter == null)
            presenter = new Presenter();
        presenter.setView(this);
        presenter.retrieveMenssagens();
        presenter.retrieveUsersOnline();

        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.chat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(presenter.getMenssagens());
        recyclerView.setAdapter(myAdapter);

        msg = (EditText) findViewById(R.id.message);
    }

    public void sendMessage(View view) {
        presenter.postMenssagem(msg.getText().toString());
    }

    @Override
    public void refreshMenssagens() {
        myAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(presenter.getMenssagens().size() - 1); //seta a lista no final
    }

    @Override
    public void clearCampo() {
        msg.setText("");
    }

    @Override
    public void refreshUsersOnline(String usersOnline) {
        TextView mUsersOnline = (TextView) findViewById(R.id.usersOnline);
        mUsersOnline.setText(usersOnline);
    }

    @Override
    public void setDialog(String message) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.show();
    }

    @Override
    public void closeDialog(){
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        presenter.imOnline(hasFocus);
    }
}
