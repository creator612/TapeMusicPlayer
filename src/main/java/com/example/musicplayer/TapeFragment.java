package com.example.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.musicplayer.MainActivity.albums;
import static com.example.musicplayer.MainActivity.musicFiles;
import static com.example.musicplayer.TapeActivity.tapesFiles;

public class TapeFragment extends Fragment {

    RecyclerView recyclerView;
    TapeAdapter tapeAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if(!(tapesFiles.size() < 1))
        {
            tapeAdapter = new TapeAdapter(getContext(), tapesFiles);
            recyclerView.setAdapter(tapeAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        return view;
    }
}
