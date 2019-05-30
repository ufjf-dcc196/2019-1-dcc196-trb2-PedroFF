package com.example.trabalho2.contract;

import android.provider.BaseColumns;

public class DbContract {
    public DbContract(){};

    public class Tarefa implements BaseColumns{
        public static final String TEXT_TYPE = " TEXT";
        public static final String INT_TYPE = " INTEGER";
        public static final String TIME_TYPE = " TIMESTAMP";
        public static final String SEP = ",";
        public static final String TABELA = "tarefa";
        public static final String TITULO = "titulo";
        public static final String DESCRICAO = "descricao";
        public static final String GRAU = "GRAU";
        public static final String ESTADO = "estado";
        public static final String DT_ATUALIZACAO = "dt_atualizacao";
        public static final String DT_LIMITE = "dt_limite";
        public static final String SQL_CRIA_TAREFA= "CREATE TABLE " + Tarefa.TABELA + " (" +
                Tarefa._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
                Tarefa.TITULO + TEXT_TYPE + SEP +
                Tarefa.DESCRICAO + TEXT_TYPE + SEP +
                Tarefa.GRAU + INT_TYPE + SEP +
                Tarefa.ESTADO + INT_TYPE + SEP +
                Tarefa.DT_ATUALIZACAO+ TIME_TYPE+ SEP +
                Tarefa.DT_LIMITE+ TIME_TYPE+ ")";
        public static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS" + Tarefa.TABELA;
    }

    public class Etiqueta implements BaseColumns{}
}
