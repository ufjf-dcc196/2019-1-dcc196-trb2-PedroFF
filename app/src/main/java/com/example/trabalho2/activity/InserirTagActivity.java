package com.example.trabalho2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.trabalho2.R;
import com.example.trabalho2.dao.TagDAO;
import com.example.trabalho2.model.Tag;

import java.io.Serializable;


public class InserirTagActivity extends AppCompatActivity {
    private EditText titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tag);
        titulo = findViewById(R.id.tituloTag);
        Button btnSalvar = findViewById(R.id.btnSalvarTag);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloTag = titulo.getText().toString();
                Tag tag = new Tag(tituloTag);
                TagDAO.getInstance().salvar(tag, InserirTagActivity.this);
                Intent intent = new Intent();
                intent.putExtra("tag", tag);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}

