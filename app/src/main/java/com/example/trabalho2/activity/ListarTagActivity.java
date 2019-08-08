package com.example.trabalho2.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.trabalho2.R;
import com.example.trabalho2.adapter.TagAdapter;
import com.example.trabalho2.dao.TagDAO;
import com.example.trabalho2.dao.TarefaTagDAO;
import com.example.trabalho2.model.Tag;

public class ListarTagActivity extends AppCompatActivity {
    private TagAdapter adapter;
    private static final int TAG_CADASTRADA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tags);
        final RecyclerView rv = findViewById(R.id.tags_recycle);
        this.adapter = new TagAdapter(TagDAO.getInstance().getTags(this));
        adapter.setOnTagClickListener((tarefaView, position) -> {
            Tag tag = adapter.getTag(position);
            Cursor cursor = TarefaTagDAO.getInstance().getTarefasByTag(ListarTagActivity.this, tag);
            if (cursor.getCount() > 0) {
                Intent intent = new Intent(ListarTagActivity.this, ListarTarefaActivity.class);
                intent.putExtra("tag", tag);
                startActivity(intent);
            } else {
                Toast.makeText(ListarTagActivity.this,
                        "Tag não está associada a tarefa.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ListarTagActivity.TAG_CADASTRADA) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Tag salva!", Toast.LENGTH_SHORT).show();
                this.adapter.setCursor(TagDAO.getInstance().getTags(ListarTagActivity.this));
            }
        }
    }
}
