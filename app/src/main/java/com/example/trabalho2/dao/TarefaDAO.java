package com.example.trabalho2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.trabalho2.contract.DBHelper;
import com.example.trabalho2.contract.DbContract;
import com.example.trabalho2.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TarefaDAO {
    private static final TarefaDAO tarefaDAO = new TarefaDAO();

    public static TarefaDAO getInstance() {
        return tarefaDAO;
    }

    private TarefaDAO() {
    }

    public Tarefa salvar(Tarefa tarefa, Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        tarefa.setId(db.insert(DbContract.Tarefa.TABELA, null, transformaParaValues(tarefa)));
        return tarefa;
    }

    private ContentValues transformaParaValues(Tarefa tarefa){
        ContentValues values = new ContentValues();
        values.put(DbContract.Tarefa.TITULO,tarefa.getTitulo());
        values.put(DbContract.Tarefa.DESCRICAO,tarefa.getDescricao());
        values.put(DbContract.Tarefa.DT_LIMITE,tarefa.getDt_limite());
        values.put(DbContract.Tarefa.DT_ATUALIZACAO, new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        values.put(DbContract.Tarefa.ESTADO,tarefa.getEstado().getCodigo());
        values.put(DbContract.Tarefa.GRAU,tarefa.getGrauDificuldade().getCodigo());
        return values;
    }

    public Integer atualizar(Tarefa tarefa, Context context){
        DBHelper dBHelper = new DBHelper(context);
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        return db.update(DbContract.Tarefa.TABELA, transformaParaValues(tarefa),DbContract.Tarefa._ID+"=?",transformaEmArgs(tarefa));
    }

    public Integer deletar(Tarefa tarefa,Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        return db.delete(DbContract.Tarefa.TABELA,DbContract.Tarefa._ID+"=?",transformaEmArgs(tarefa));
    }

    private String[] transformaEmArgs(Tarefa tarefa) {
        return new String[]{tarefa.getId().toString()};
    }

    public Cursor getTarefasPorEstado(Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getReadableDatabase();
        String[] consulta = {
                DbContract.Tarefa._ID,
                DbContract.Tarefa.TITULO,
                DbContract.Tarefa.DESCRICAO,
                DbContract.Tarefa.DT_ATUALIZACAO,
                DbContract.Tarefa.DT_LIMITE,
                DbContract.Tarefa.GRAU,
                DbContract.Tarefa.ESTADO
        };
        String sort = DbContract.Tarefa.ESTADO + " ASC";
        return db.query(DbContract.Tarefa.TABELA, consulta, null, null, null, null, sort);
    }

}
