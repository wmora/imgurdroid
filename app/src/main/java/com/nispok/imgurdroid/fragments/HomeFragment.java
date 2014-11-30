package com.nispok.imgurdroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halfbit.tinybus.Subscribe;
import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.adapter.GalleryAdapter;
import com.nispok.imgurdroid.events.BusProvider;
import com.nispok.imgurdroid.events.ImgurServiceEvents;
import com.nispok.imgurdroid.models.Gallery;
import com.nispok.imgurdroid.services.Imgur;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final String SAVED_GALLERY_DATA = "SAVED_GALLERY_DATA";

    private SwipeRefreshLayout galleryContainer;
    private RecyclerView gallery;
    private GalleryAdapter galleryAdapter;
    private StaggeredGridLayoutManager layoutManager;

    private Gallery galleryData = new Gallery();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            galleryData = (Gallery) savedInstanceState.getSerializable(SAVED_GALLERY_DATA);
        }

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loadViews(view);

        return view;
    }

    private void loadViews(View container) {
        loadGalleryContainerView(container);
        loadGalleryView(container);
    }

    private void loadGalleryContainerView(View container) {
        galleryContainer = (SwipeRefreshLayout) container.findViewById(R.id.gallery_container);
        galleryContainer.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        loadGallery();
    }

    private void loadGalleryView(View container) {
        gallery = (RecyclerView) container.findViewById(R.id.gallery);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gallery.setLayoutManager(layoutManager);
        galleryAdapter = new GalleryAdapter(galleryData.getData());
        gallery.setAdapter(galleryAdapter);
        gallery.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        Log.d(TAG, "End has been reached, load more");
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.bus().register(this);
        if (shouldLoadGallery()) {
            loadGallery();
        }
    }

    private boolean shouldLoadGallery() {
        return galleryData == null || galleryData.getData().isEmpty();
    }

    private void loadGallery() {
        Imgur.getGallery();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.bus().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_GALLERY_DATA, galleryData);
    }

    @Subscribe
    public void onEvent(ImgurServiceEvents.GallerySuccessEvent event) {
        galleryContainer.setRefreshing(false);
        galleryData = event.getResult();
        galleryAdapter.setDataset(galleryData.getData());
    }

    @Subscribe
    public void onError(ImgurServiceEvents.ErrorEvent event) {
        galleryContainer.setRefreshing(false);
    }
}
