package com.example.trabalho2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.trabalho2.R;
import com.example.trabalho2.adapter.TarefaAdapter;
import com.example.trabalho2.dao.TarefaDAO;
import com.example.trabalho2.dao.TarefaTagDAO;
import com.example.trabalho2.model.Tag;

public class ListarTarefaActivity extends AppCompatActivity {
    private TarefaAdapter adapter;
    private Tag tag;
    private static final int DELETAR_TAREFA = 1;
    private static final int EDITAR_TAREFA = 2;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefas_por_tags);
        Bundle bundle = getIntent().getExtras();
        this.titulo = findViewById(R.id.tituloTarefasPorTag);
        if (bundle != null) {
            this.tag = (Tag) bundle.get("tag");
            this.titulo.append(" " + tag.getTag());
        }
        final RecyclerView rv = findViewById(R.id.tarefasPorTag);

        this.adapter = new TarefaAdapter(TarefaTagDAO.getInstance().getTarefasByTag(this, tag), tag);

        adapter.setOnTarefaClickListener(new TarefaAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClick(View tarefaView, int position) {
                Intent intent = new Intent(ListarTarefaActivity.this, EditarTarefaActivity.class);
                intent.putExtra("tarefa", adapter.getTarefa(position));
                startActivityForResult(intent, ListarTarefaActivity.EDITAR_TAREFA);
            }
        });
        rv.setAdapter(adapter);

    }

}
