package com.example.trabalho2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.trabalho2.contract.DBHelper;
import com.example.trabalho2.contract.DbContract;
import com.example.trabalho2.model.Tag;

public class TagDAO {
    private static final TagDAO tagDAO = new TagDAO();

    public static TagDAO getInstance() {
        return tagDAO;
    }

    private TagDAO() {
    }

    public Tag salvar(Tag tag, Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        tag.setId(db.insert(DbContract.Tag.NOME_TABELA, null, transformaParaValues(tag)));
        return tag;
    }

    private ContentValues transformaParaValues(Tag tag){
        ContentValues values = new ContentValues();
        values.put(DbContract.Tag.TITULO,tag.getTag());
        return values;
    }


    private String[] transformaEmArgs(Tag tag) {
        return new String[]{tag.getId().toString()};
    }

    public Cursor getTarefasPorEstado(Context context){
        DBHelper tarefaDBHelper = new DBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getReadableDatabase();
        String[] consulta = {
                DbContract.Tag._ID,
                DbContract.Tag.TITULO
        };
        String sort = DbContract.Tag.TITULO+ " ASC";
        return db.query(DbContract.Tag.NOME_TABELA, consulta, null, null, null, null, sort);
    }

    public Cursor getTags(Context context){
        DBHelper dBHelper = new DBHelper(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        String[] visao = {
                DbContract.Tag._ID,
                DbContract.Tag.TITULO
        };
        String sort = DbContract.Tag.TITULO+ " ASC";
        return db.query(DbContract.Tag.NOME_TABELA, visao, null, null, null, null, sort);
    }

}
