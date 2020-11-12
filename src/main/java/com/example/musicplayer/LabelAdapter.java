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

import static com.example.musicplayer.TapeActivity.selectedLabelId;
import static com.example.musicplayer.TapeActivity.selectedTapeId;

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.MyHolder>{

    private Context mContext;
    private ArrayList<DrawableFiles> labelImages;
    int row_index = -1;
    View view;

    public LabelAdapter(Context mContext, ArrayList<DrawableFiles> labelImages) {
        this.mContext = mContext;
        this.labelImages = labelImages;
    }

    @NonNull
    @Override
    public LabelAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.tape_item, parent, false);
        return new LabelAdapter.MyHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final LabelAdapter.MyHolder holder, final int position) {

        final int labelResourseID = labelImages.get(position).getResourseID();
        Glide.with(mContext).load(labelResourseID).into(holder.tape_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                selectedLabelId = labelResourseID;
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
        return labelImages.size();
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
