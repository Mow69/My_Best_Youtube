package com.ita.mybestyoutube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {

    private List<YoutubeVideo> youtubeVideos;
    private OnItemClickListener onItemClickListener;

    // basé sur le layout de l'item
    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public TextView tvDescription;
        public TextView tvUrl;
        public TextView tvCategorie;

        public YoutubeVideoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            tvCategorie = itemView.findViewById(R.id.tvCategorie);
        }
    }

    // constructeur qui renseigne la liste de youtubeVideos
    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnItemClickListener onItemClickListener) {
        this.youtubeVideos = youtubeVideos;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtubevideo_item, parent, false);

        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        // récupère le youtubeVideo à la positon fournie
        YoutubeVideo youtubeVideo = youtubeVideos.get(position);

        // met les information du youtubeVideo qui est dans la liste dans la vue de l'item
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getDescription());
        holder.tvUrl.setText(youtubeVideo.getUrl());
        holder.tvCategorie.setText(youtubeVideo.getCategorie());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(youtubeVideo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo item);
    }
}
