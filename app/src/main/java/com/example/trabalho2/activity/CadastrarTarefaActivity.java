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

public class CadastrarTarefaActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText dtLimite;
    private EditText descricao;
    private Spinner estado;
    private Spinner grau;
    private RecyclerView checkTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        titulo = findViewById(R.id.tituloTarefa);
        dtLimite = findViewById(R.id.dataTarefa);
        descricao = findViewById(R.id.descricaoTarefa);
        estado = findViewById(R.id.estadoTarefa);
        final ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_layout_item, Estado.values());
        estado.setAdapter(adapter);
        grau = findViewById(R.id.grauTarefa);
        final ArrayAdapter grauAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout_item, Grau.values());
        grau.setAdapter(grauAdapter);
        checkTag = findViewById(R.id.tags);
        final TarefaTagAdapter tarefaTagAdapter = new TarefaTagAdapter(TagDAO.getInstance().getTags(CadastrarTarefaActivity.this));
        checkTag.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        checkTag.setAdapter(tarefaTagAdapter);
        Button btnSalvar = findViewById(R.id.btnSalvarTarefa);
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
                    TarefaDAO.getInstance().salvar(tarefa, CadastrarTarefaActivity.this);
                    TarefaTagDAO.getInstance().deletar(tarefa,CadastrarTarefaActivity.this);
                    for (Tag t:tarefa.getTags()) {
                        TarefaTagDAO.getInstance().salvar(tarefa, t,CadastrarTarefaActivity.this);
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
    }
}
