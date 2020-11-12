package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.musicplayer.TapeActivity.selectedTapeId;


public class TapeAdapter extends RecyclerView.Adapter<TapeAdapter.MyHolder> {

    private Context mContext;
    private ArrayList<DrawableFiles> tapeImages;
    int row_index = -1;
    View view;

    public TapeAdapter(Context mContext, ArrayList<DrawableFiles> tapeImages) {
        this.mContext = mContext;
        this.tapeImages = tapeImages;
    }

    @NonNull
    @Override
    public TapeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.tape_item, parent, false);
        return new TapeAdapter.MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final TapeAdapter.MyHolder holder, final int position) {

        final int tapeResourseID = tapeImages.get(position).getResourseID();
        Glide.with(mContext).load(tapeResourseID).into(holder.tape_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                selectedTapeId = tapeResourseID;
                notifyDataSetChanged();

            }
        });

        if (row_index == position)
        {
            holder.tape_image.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.tape_image.setBackgroundColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return tapeImages.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView tape_image;
        public MyHolder(View itemView){
            super(itemView);
            tape_image = itemView.findViewById(R.id.tape_image);
        }
    }

    private byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }


}
