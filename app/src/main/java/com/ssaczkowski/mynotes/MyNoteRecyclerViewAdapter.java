package com.ssaczkowski.mynotes;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final NoteInteractionListener mListener;

    public MyNoteRecyclerViewAdapter(List<Note> items,NoteInteractionListener listener) {
        mValues = items;
        mListener = listener;
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
                if(null!= mListener){
                    mListener.favoriteNoteClick(holder.mItem);
                }
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
        public Note mItem;

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
}