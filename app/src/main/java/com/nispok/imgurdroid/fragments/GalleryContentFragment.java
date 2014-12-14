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
import com.nispok.imgurdroid.listeners.OnGalleryScrollListener;
import com.nispok.imgurdroid.models.Gallery;
import com.nispok.imgurdroid.models.GalleryInfo;
import com.nispok.imgurdroid.services.Imgur;

public class GalleryContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        OnGalleryScrollListener.GalleryScrollListener {

    private static final String TAG = GalleryContentFragment.class.getSimpleName();
    private static final String SAVED_GALLERY_DATA = "SAVED_GALLERY_DATA";
    private static final String SAVED_GALLERY_INFO = "SAVED_GALLERY_INFO";

    private SwipeRefreshLayout galleryContainer;
    private GalleryAdapter galleryAdapter;

    private Gallery galleryData = new Gallery();
    private GalleryInfo galleryInfo = new GalleryInfo();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            galleryData = (Gallery) savedInstanceState.getSerializable(SAVED_GALLERY_DATA);
            galleryInfo = (GalleryInfo) savedInstanceState.getSerializable(SAVED_GALLERY_INFO);
        }

        View view = inflater.inflate(R.layout.fragment_gallery_content, container, false);

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
        galleryContainer.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadGallery();
    }

    private void loadGalleryView(View container) {
        RecyclerView gallery = (RecyclerView) container.findViewById(R.id.gallery);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        gallery.setLayoutManager(layoutManager);
        galleryAdapter = new GalleryAdapter();
        gallery.setAdapter(galleryAdapter);
        gallery.setOnScrollListener(new OnGalleryScrollListener(layoutManager, this));
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
        galleryContainer.setRefreshing(true);
        Imgur.getGallery(galleryInfo);
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
        outState.putSerializable(SAVED_GALLERY_INFO, galleryInfo);
    }

    @Subscribe
    public void onGallerySuccess(ImgurServiceEvents.GallerySuccessEvent event) {
        galleryContainer.setRefreshing(false);
        galleryInfo.setPage(galleryInfo.getPage() + 1);
        galleryData = event.getResult();
        galleryAdapter.add(galleryData.getData());
    }

    @Subscribe
    public void onError(ImgurServiceEvents.ErrorEvent event) {
        Log.e(TAG, event.getResult().toString());
        galleryContainer.setRefreshing(false);
    }

    @Override
    public void onScrollEndReached() {
        if (!galleryContainer.isRefreshing()) {
            loadGallery();
        }
    }

}
