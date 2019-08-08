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
import org.jetbrains.annotations.NotNull;

public class TagAdapter extends  RecyclerView.Adapter<TagAdapter.ViewHolder>{
    private Cursor cursor;
    private onTagClickListener listener;

    public interface onTagClickListener {
        void onTagClick(View tarefaView, int position);
    }

    public void setOnTagClickListener(onTagClickListener listener) {
        this.listener = listener;
    }

    public TagAdapter(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public Tag getTag(int position) {
        int titulo = cursor.getColumnIndexOrThrow(DbContract.Tag.TITULO);
        int id = cursor.getColumnIndexOrThrow(DbContract.Tag._ID);
        cursor.moveToPosition(position);
        Tag tag;
        tag = new Tag(cursor.getLong(id), cursor.getString(titulo));
        return tag;
    }

    @NonNull
    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.tags_layout, viewGroup, false);
        return new ViewHolder(tarefaView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.ViewHolder viewHolder, int i) {
        final Tag tag = getTag(i);
        viewHolder.txtTitulo.setText(tag.toString());
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitulo = itemView.findViewById(R.id.tag_recycle);
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
                            listener.onTagClick(v, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
