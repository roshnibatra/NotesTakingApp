package com.example.android.notestakingapp;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notestakingapp.databinding.NoteItemsBinding;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    List<Note> noteList;
    private onItemClickListener listener;

    public NoteAdapter() {
    }

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return noteList.get(position);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NoteItemsBinding itemsBinding = NoteItemsBinding.inflate(layoutInflater,parent,false);

        return new NoteHolder(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note= noteList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return noteList != null ? noteList.size() : 0;
    }

    class NoteHolder extends RecyclerView.ViewHolder {
       private NoteItemsBinding itemsBinding;


        public NoteHolder(NoteItemsBinding itemsBinding) {
            super(itemsBinding.getRoot());
            this.itemsBinding= itemsBinding;

            itemsBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener !=null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(noteList.get(position));
                    }
                }
            });
        }

        public void bind(Note note) {
            itemsBinding.setNote(note);
            itemsBinding.executePendingBindings();
        }


    }

    public interface onItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnClickListener(onItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }


}
