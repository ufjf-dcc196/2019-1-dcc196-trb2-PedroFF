package com.example.trabalho2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.trabalho2.R;
import com.example.trabalho2.adapter.TarefaTagAdapter;
import com.example.trabalho2.dao.TagDAO;
import com.example.trabalho2.dao.TarefaDAO;
import com.example.trabalho2.dao.TarefaTagDAO;
import com.example.trabalho2.model.Estado;
import com.example.trabalho2.model.Grau;
import com.example.trabalho2.model.Tag;
import com.example.trabalho2.model.Tarefa;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EditarTarefaActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText dtLimite;
    private EditText descricao;
    private Spinner estado;
    private Spinner grau;
    private RecyclerView checkTag;
    private Tarefa tarefa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarefa);
        Bundle bundle = getIntent().getExtras();
        this.tarefa = (Tarefa) bundle.get("tarefa");
        this.titulo = findViewById(R.id.tituloTarefaEditar);
        this.descricao = findViewById(R.id.descricaoTarefaEditar);
        this.dtLimite = findViewById(R.id.dataTarefaEditar);
        this.grau = findViewById(R.id.grauTarefaEditar);
        this.estado = findViewById(R.id.estadoTarefaEditar);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_layout_item, Estado.values());
        adapter.setDropDownViewResource(R.layout.spinner_layout_item);
        ArrayAdapter adapterGrau = new ArrayAdapter<>(this, R.layout.spinner_layout_item, Grau.values());
        adapterGrau.setDropDownViewResource(R.layout.spinner_layout_item);
        this.grau.setAdapter(adapterGrau);
        this.estado.setAdapter(adapter);
        this.estado.setSelection(adapter.getPosition(this.tarefa.getEstado()));
        this.grau.setSelection(adapterGrau.getPosition(this.tarefa.getGrauDificuldade()));
        dtLimite.setText(this.tarefa.getDt_limite());
        titulo.setText(this.tarefa.getTitulo());
        descricao.setText(this.tarefa.getDescricao());
        checkTag = findViewById(R.id.tagsEditar);
        final TarefaTagAdapter tarefaTagAdapter = new TarefaTagAdapter(TagDAO.getInstance().getTags(EditarTarefaActivity.this));
        tarefaTagAdapter.setItemStateArray(TarefaTagDAO.getInstance().getTagByTarefa(EditarTarefaActivity.this, this.tarefa));
        checkTag.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        checkTag.setAdapter(tarefaTagAdapter);
        Button btnSalvar = findViewById(R.id.btnSalvarTarefaEditar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estado estadoT = (Estado) estado.getSelectedItem();
                Grau grauT = (Grau) grau.getSelectedItem();
                String dtLimiteT = dtLimite.getText().toString();
                String tituloT = titulo.getText().toString();
                String descricaoT = descricao.getText().toString();
                SparseBooleanArray escolhidos = tarefaTagAdapter.getItemStateArray();
                List<Tag> tagList = new ArrayList<>();
                for(int i = 0; i < escolhidos.size();i++){
                    int key = escolhidos.keyAt(i);
                    tagList.add(tarefaTagAdapter.getTag(key));
                }
                try {
                    Tarefa tarefa = new Tarefa(tituloT, descricaoT, grauT, estadoT, tagList, dtLimiteT);
                    TarefaDAO.getInstance().salvar(tarefa, EditarTarefaActivity.this);
                    TarefaTagDAO.getInstance().deletar(tarefa,EditarTarefaActivity.this);
                    for (Tag t:tarefa.getTags()) {
                        TarefaTagDAO.getInstance().salvar(tarefa, t,EditarTarefaActivity.this);
                    }
                    Intent intent = new Intent();
                    intent.putExtra("tarefa", tarefa);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        Button btnDeletar = findViewById(R.id.btnExcluirTarefa);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaDAO.getInstance().deletar(tarefa, EditarTarefaActivity.this);
            }
        });
    }
}
