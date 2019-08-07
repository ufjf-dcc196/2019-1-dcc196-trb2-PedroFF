package com.example.trabalho2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.trabalho2.contract.DBHelper;
import com.example.trabalho2.contract.DbContract;
import com.example.trabalho2.model.Tag;
import com.example.trabalho2.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TarefaTagDAO {
    private static final TarefaTagDAO tarefaTagDAO = new TarefaTagDAO();

    private final String TarefasPorTag = String.format(Locale.getDefault(), "SELECT t.* FROM %s t INNER JOIN %s te ON" +
                    " t.%s = te.%s WHERE %s = ? ORDER BY %s",
            DbContract.Tarefa.TABELA,
            DbContract.TarefaTag.NOME_TABELA,
            DbContract.Tarefa._ID,
            DbContract.TarefaTag.TAREFA,
            DbContract.TarefaTag.TAG,
            DbContract.Tarefa.ESTADO);

    private final String TagsPorTarefa = String.format(Locale.getDefault(), "SELECT t.* FROM %s t INNER JOIN %s te ON" +
                    " t.%s = te.%s WHERE %s = ? ORDER BY %s",
            DbContract.Tag.NOME_TABELA,
            DbContract.TarefaTag.NOME_TABELA,
            DbContract.Tag._ID,
            DbContract.TarefaTag.TAG,
            DbContract.TarefaTag.TAREFA,
            DbContract.Tag.TITULO);

    public static TarefaTagDAO getInstance() {
        return tarefaTagDAO;
    }

    private TarefaTagDAO() {
    }

    public Long salvar(Tarefa tarefa, Tag tag, Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        return db.insert(DbContract.TarefaTag.NOME_TABELA, null, transformaParaValues(tarefa, tag));

    }

    private ContentValues transformaParaValues(Tarefa tarefa, Tag tag){
        ContentValues values = new ContentValues();
        values.put(DbContract.TarefaTag.TAG,tag.getId());
        values.put(DbContract.TarefaTag.TAREFA,tarefa.getId());
        return values;
    }


    public Integer deletar(Tarefa tarefa, Tag tag, Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        return db.delete(DbContract.TarefaTag.NOME_TABELA,DbContract.TarefaTag.TAREFA+"=?",transformaEmArgs(tarefa));
    }

    private String[] transformaEmArgs(Tarefa tarefa) {
        return new String[]{tarefa.getId().toString()};
    }


    public Cursor getTarefasByEtiqueta(Context context, Tag tag) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery(TarefasPorTag, new String[]{String.valueOf(tag.getId())});
    }

    public Cursor getEtiquetasByTarefa(Context context, Tarefa tarefa) {
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getReadableDatabase();
        return db.rawQuery(TagsPorTarefa, new String[]{String.valueOf(tarefa.getId())});
    }
}
