package com.nispok.imgurdroid.listeners;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * {@link android.support.v7.widget.RecyclerView.OnScrollListener} that, provided a
 * {@link android.support.v7.widget.StaggeredGridLayoutManager} and a
 * {@link com.nispok.imgurdroid.listeners.OnGalleryScrollListener.GalleryScrollListener}, will
 * notify whenever the end of the list has been reached
 */
public class OnGalleryScrollListener extends RecyclerView.OnScrollListener {

    private StaggeredGridLayoutManager layoutManager;
    private GalleryScrollListener listener;

    public interface GalleryScrollListener {
        public void onScrollEndReached();
    }

    public OnGalleryScrollListener(StaggeredGridLayoutManager layoutManager,
                                   GalleryScrollListener listener) {
        this.layoutManager = layoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItemCount = layoutManager.getItemCount();
        int[] itemPositions = new int[layoutManager.getSpanCount()];
        layoutManager.findLastVisibleItemPositions(itemPositions);
        for (int itemPosition : itemPositions) {
            if (itemPosition >= (totalItemCount - 1)) {
                listener.onScrollEndReached();
            }
        }
    }
}
