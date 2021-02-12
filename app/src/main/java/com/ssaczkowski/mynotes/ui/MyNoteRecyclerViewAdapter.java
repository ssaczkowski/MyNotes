package com.ssaczkowski.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssaczkowski.mynotes.viewmodel.NewNoteDialogViewModel;
import com.ssaczkowski.mynotes.R;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;

import java.util.List;


public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;
    private NewNoteDialogViewModel mViewModel;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        mViewModel = ViewModelProviders.of((AppCompatActivity) ctx).get(NewNoteDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mContent.setText(mValues.get(position).getContent());

        if(holder.mItem.isFavorite())
            holder.mFavorite.setImageResource(R.drawable.ic_baseline_star_24);
        else
            holder.mFavorite.setImageResource(R.drawable.ic_baseline_star_border_24);

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mItem.isFavorite()) {
                    holder.mItem.setFavorite(false);
                    holder.mFavorite.setImageResource(R.drawable.ic_baseline_star_border_24);
                }else {
                    holder.mItem.setFavorite(true);
                    holder.mFavorite.setImageResource(R.drawable.ic_baseline_star_24);
                }
                mViewModel.updateNote(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mContent;
        public final ImageView mFavorite;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.textViewTitle);
            mContent = (TextView) view.findViewById(R.id.textViewContent);
            mFavorite = (ImageView) view.findViewById(R.id.imageViewFavorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }

    public void updateNotes(List<NoteEntity> noteEntities){
        this.mValues =noteEntities;
        notifyDataSetChanged();
    }
}