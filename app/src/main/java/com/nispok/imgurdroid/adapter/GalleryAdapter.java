package com.nispok.imgurdroid.adapter;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.models.GalleryResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryResult> dataset = new ArrayList<>();

    public GalleryAdapter(List<GalleryResult> dataset) {
        this.dataset = dataset;
    }

    public void setDataset(List<GalleryResult> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gallery_result,
                parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.updateView(dataset.get(position));
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {

        private ImageView galleryResultImage;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            galleryResultImage = (ImageView) itemView.findViewById(R.id.gallery_result_image);
        }

        public void updateView(GalleryResult galleryResult) {
            Picasso.with(itemView.getContext())
                    .cancelRequest(galleryResultImage);

            ViewGroup.LayoutParams params = galleryResultImage.getLayoutParams();
            params.height = galleryResult.getImageHeight();

            galleryResultImage.setLayoutParams(params);

            Picasso.with(itemView.getContext())
                    .load(galleryResult.getImageUrl())
                    .transform(new ImageTransformation(itemView, galleryResult.getImageUrl()))
                    .into(galleryResultImage);
        }
    }

    private static class ImageTransformation implements Transformation {

        private View holder;
        private String id;

        public ImageTransformation(View holder, String id) {
            this.holder = holder;
            this.id = id;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int width = holder.getWidth();
            Bitmap transformedBitmap = ThumbnailUtils.extractThumbnail(source, width,
                    source.getHeight(), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            if (transformedBitmap != source) {
                source.recycle();
            }
            return transformedBitmap;
        }

        @Override
        public String key() {
            return String.format("%s_%d", id, holder.getWidth());
        }
    }

}