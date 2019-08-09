package com.example.trabalho2.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "ToDoList.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.Tarefa.SQL_CRIA_TAREFA);
        db.execSQL(DbContract.Tag.SQL_CRIA_TAG);
        db.execSQL(DbContract.TarefaTag.SQL_CRIA_TAREFA_TAG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Tarefa.SQL_DROP_TAREFA);
        db.execSQL(DbContract.Tag.SQL_DROP_TAG);
        db.execSQL(DbContract.TarefaTag.SQL_DROP_TAREFA_TAG);
        onCreate(db);
    }
}
