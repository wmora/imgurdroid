/*
 * Copyright (c) 2014 William Mora
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nispok.imgurdroid.adapters;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.models.GalleryResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter used for our image list
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryResult> dataset = new ArrayList<>();

    public GalleryAdapter() {
        // Nothing to do here
    }

    /**
     * Adds all items to the end of the list
     *
     * @param items items to be inserted
     */
    public void add(List<GalleryResult> items) {
        dataset.addAll(items);
        notifyItemRangeInserted(dataset.size() - items.size(), items.size());
    }

    /**
     * Clears all items in lists
     */
    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gallery_result,
                parent, false);
        return new GalleryViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.updateView(dataset.get(position));
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {

        private ImageView galleryResultImage;
        private TextView galleryResultTitle;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            galleryResultImage = (ImageView) itemView.findViewById(R.id.gallery_result_image);
            galleryResultTitle = (TextView) itemView.findViewById(R.id.gallery_result_title);
        }

        public void updateView(GalleryResult galleryResult) {
            updateImage(galleryResult);
            updateTitle(galleryResult);
        }

        private void updateImage(GalleryResult galleryResult) {
            Picasso.with(itemView.getContext())
                    .cancelRequest(galleryResultImage);

            if (galleryResult.shouldDisplayNsfwWarning()) {
                Picasso.with(itemView.getContext())
                        .load(R.drawable.ic_launcher)
                        .into(galleryResultImage);
            }

            ViewGroup.LayoutParams params = galleryResultImage.getLayoutParams();
            params.height = galleryResult.getImageHeight();

            galleryResultImage.setLayoutParams(params);

            Picasso.with(itemView.getContext())
                    .load(galleryResult.getImageUrl())
                    .transform(new ImageTransformation(itemView, galleryResult.getImageUrl()))
                    .into(galleryResultImage);

        }

        private void updateTitle(GalleryResult galleryResult) {
            if (galleryResult.shouldDisplayNsfwWarning()) {
                galleryResultTitle.setText(itemView.getContext().getText(R.string.nsfw_text));
                return;
            }
            galleryResultTitle.setText(galleryResult.getTitle());
        }
    }

    /**
     * Custom Picasso {@link com.squareup.picasso.Transformation} for the downloaded images
     */
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
            if (width <= 0) {
                return source;
            }
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
