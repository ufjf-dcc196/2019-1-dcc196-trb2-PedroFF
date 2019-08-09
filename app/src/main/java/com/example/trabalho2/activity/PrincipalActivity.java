package com.example.trabalho2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.trabalho2.R;
import com.example.trabalho2.adapter.TarefaAdapter;
import com.example.trabalho2.dao.TarefaDAO;
import com.example.trabalho2.dao.TarefaTagDAO;


public class PrincipalActivity extends AppCompatActivity {
    private TarefaAdapter adapter;
    private static final int CADASTRAR_TAREFA = 1;
    private static final int EDITAR_TAREFA = 2;
    private static final int DELETAR_TAREFA = 4;
    private static final int CADASTRAR_TAG = 3;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        rv = findViewById(R.id.tarefas_recycle);
        this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
        this.adapter.setOnTarefaClickListener(new TarefaAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClick(View tarefaView, int position) {
                Intent intent = new Intent(PrincipalActivity.this, EditarTarefaActivity.class);
                intent.putExtra("tarefa", adapter.getTarefa(position));
                startActivityForResult(intent, PrincipalActivity.EDITAR_TAREFA);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(this.adapter);

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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PrincipalActivity.DELETAR_TAREFA) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Tarefa deletada", Toast.LENGTH_SHORT).show();
                this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
                rv.setAdapter(this.adapter);
            }
        }
        if (requestCode == PrincipalActivity.EDITAR_TAREFA) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Tarefa alterada", Toast.LENGTH_SHORT).show();
                this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
                rv.setAdapter(this.adapter);
            }
        }
        if (requestCode == PrincipalActivity.CADASTRAR_TAREFA) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Tarefa cadastrada", Toast.LENGTH_SHORT).show();
                this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
                rv.setAdapter(this.adapter);
            }
        }
        if (requestCode == PrincipalActivity.CADASTRAR_TAG) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Tag cadastrada", Toast.LENGTH_SHORT).show();
                this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasPorEstado(this), null);
                rv.setAdapter(this.adapter);
            }
        }

    }
}
