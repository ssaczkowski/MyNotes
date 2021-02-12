package com.ssaczkowski.mynotes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ssaczkowski.mynotes.viewmodel.NewNoteDialogViewModel;
import com.ssaczkowski.mynotes.R;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class NoteFragment extends Fragment{


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private List<NoteEntity> mNotesList;
    private MyNoteRecyclerViewAdapter mAdapter;
    private NewNoteDialogViewModel mNoteViewModel;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (view.getId() == R.id.listPortrait) {
               //recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount,
                        StaggeredGridLayoutManager.VERTICAL));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int columns = (int) (dpWidth / 180);

                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columns,
                        StaggeredGridLayoutManager.VERTICAL));
            }

            mNotesList = new ArrayList<>();

            mAdapter = new MyNoteRecyclerViewAdapter(mNotesList,getActivity());

            recyclerView.setAdapter(mAdapter);

            setViewModelNoteObserver();
        }
        return view;
    }

    private void setViewModelNoteObserver() {
        mNoteViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
        mNoteViewModel.getAllNotes().observe(getActivity(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                mAdapter.updateNotes(noteEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu_note_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_note:
                showNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showNewNote() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NewNoteDialogFragment dialogNewNote = new NewNoteDialogFragment();
        dialogNewNote.show(fm,"NewNoteDialogFragment");
    }
}