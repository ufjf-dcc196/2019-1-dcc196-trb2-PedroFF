package com.example.trabalho2.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import com.example.trabalho2.R;
import com.example.trabalho2.contract.DbContract;
import com.example.trabalho2.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TarefaTagAdapter extends RecyclerView.Adapter<TarefaTagAdapter.ViewHolder> {
    private Cursor cursor;
    private OnTarefaTagClickListener listener;
    private SparseBooleanArray listaTags = new SparseBooleanArray();

    @NonNull
    @Override
    public TarefaTagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.check_tag_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CheckedTextView checkTag;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkTag = itemView.findViewById(R.id.check_tag);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (!listaTags.get(adapterPosition, false)) {
                checkTag.setChecked(true);
                listaTags.put(adapterPosition, true);
            } else {
                checkTag.setChecked(false);
                listaTags.put(adapterPosition, false);
            }
        }
        void bind(int position) {
            if (!listaTags.get(position, false)) {
                checkTag.setChecked(false);
            } else {
                checkTag.setChecked(true);
            }
            Tag tag = TarefaTagAdapter.this.getTag(position);
            checkTag.setText(tag.getTag());
        }
    }

    public Tag getTag(int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(DbContract.Tag.TITULO);
        int idxId = cursor.getColumnIndexOrThrow(DbContract.Tag._ID);
        cursor.moveToPosition(position);
        Tag tag;
        tag = new Tag(cursor.getLong(idxId), cursor.getString(idxTitulo));
        return tag;
    }

    private List<Tag> getListaTag(Cursor cursor){
        java.util.List<Tag> tagLista = new ArrayList<>();
        for (int i = 0; i <cursor.getCount();i++){
            int titulo = cursor.getColumnIndexOrThrow(DbContract.Tag.TITULO);
            int id = cursor.getColumnIndexOrThrow(DbContract.Tag._ID);
            cursor.moveToPosition(i);
            Tag tag;
            tag = new Tag(cursor.getLong(id), cursor.getString(titulo));
            tagLista.add(tag);
        }
        return tagLista;
    }

    private interface OnTarefaTagClickListener {
        void onTagClick(View tarefaView, int position);
    }

    public void setOnTagClickListener(OnTarefaTagClickListener listener) {
        this.listener = listener;
    }

    public TarefaTagAdapter(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public SparseBooleanArray getItemStateArray() {
        return listaTags;
    }

    public void setItemStateArray(Cursor cursor) {
        SparseBooleanArray tagLista = new SparseBooleanArray();
        List<Tag> tagsTarefa = getListaTag(cursor);
        for (int i =0; i<this.cursor.getCount();i++){
            if(tagsTarefa.contains(getTag(i))) {
                tagLista.put(i,true );
            }
        }
        this.listaTags = tagLista;
    }


}
