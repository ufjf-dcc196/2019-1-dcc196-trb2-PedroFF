package com.example.trabalho2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.example.trabalho2.R;
import com.example.trabalho2.adapter.TarefaAdapter;
import com.example.trabalho2.dao.TarefaDAO;


public class PrincipalActivity extends AppCompatActivity {
    private TarefaAdapter adapter;
    private static final int CADASTRAR_TAREFA = 1;
    private static final int EDITAR_TAREFA = 2;
    private static final int CADASTRAR_TAG = 3;
//    private static final int LISTAR_TAGS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        RecyclerView rv = findViewById(R.id.tarefas_recycle);
        this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
        adapter.setOnTarefaClickListener(new TarefaAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClick(View tarefaView, int position) {
                Intent intent = new Intent(PrincipalActivity.this, EditarTarefaActivity.class);
                intent.putExtra("tarefa", adapter.getTarefa(position));
                startActivityForResult(intent, PrincipalActivity.EDITAR_TAREFA);
            }
        });

        rv.setAdapter(adapter);

        Button btnTarefa = findViewById(R.id.novaTarefa);
        btnTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CadastrarTarefaActivity.class);
                startActivityForResult(intent, PrincipalActivity.CADASTRAR_TAREFA);
            }
        });

        Button btnTag = findViewById(R.id.novaTag);
        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, InserirTagActivity.class);
                startActivityForResult(intent, PrincipalActivity.CADASTRAR_TAG);
            }
        });
        Button btnListarTags = findViewById(R.id.btnListar);
        btnListarTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, ListarTagActivity.class);
                startActivity(intent);
            }
        });
    }
}
