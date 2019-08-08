package com.example.trabalho2.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.trabalho2.R;
import com.example.trabalho2.contract.DbContract;
import com.example.trabalho2.model.Tag;
import com.example.trabalho2.model.Tarefa;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    private Cursor cursor;
    private OnTarefaClickListener listener;
    private final Tag tag;

    public interface OnTarefaClickListener {
        void onTarefaClick(View tarefaView, int position);
    }

    public void setOnTarefaClickListener(OnTarefaClickListener listener) {
        this.listener = listener;
    }

    public TarefaAdapter(Cursor c, Tag tag) {
        cursor = c;
        this.tag = tag;
        notifyDataSetChanged();
    }

    public Tarefa getTarefa(int position) {
        int titulo = cursor.getColumnIndexOrThrow(DbContract.Tarefa.TITULO);
        int descricao = cursor.getColumnIndexOrThrow(DbContract.Tarefa.DESCRICAO);
        int dtAtualizacao = cursor.getColumnIndexOrThrow(DbContract.Tarefa.DT_ATUALIZACAO);
        int dtLimite = cursor.getColumnIndexOrThrow(DbContract.Tarefa.DT_LIMITE);
        int estado = cursor.getColumnIndexOrThrow(DbContract.Tarefa.ESTADO);
        int grau = cursor.getColumnIndexOrThrow(DbContract.Tarefa.GRAU);
        int id = cursor.getColumnIndexOrThrow(DbContract.Tarefa._ID);
        cursor.moveToPosition(position);
        Tarefa tarefa = null;
        try {
            tarefa = new Tarefa(cursor.getLong(id),
                    cursor.getString(titulo),
                    cursor.getString(descricao),
                    cursor.getInt(grau),
                    cursor.getInt(estado),
                    cursor.getString(dtLimite),
                    cursor.getString(dtAtualizacao));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tarefa;
    }

    @NonNull
    @Override
    public TarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.tarefas_layout, viewGroup, false);
        return new ViewHolder(tarefaView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.ViewHolder viewHolder, int i) {
        final Tarefa tarefa = getTarefa(i);
        viewHolder.txtTitulo.setText(tarefa.toString());
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitulo = itemView.findViewById(R.id.tarefa_recycle);
        private final Context context;

        private ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onTarefaClick(v, position);
                        }
                    }
                }
            });
        }
    }


}
