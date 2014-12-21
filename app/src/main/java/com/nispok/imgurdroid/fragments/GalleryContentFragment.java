package com.nispok.imgurdroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.nispok.imgurdroid.adapters.GalleryAdapter;
import com.nispok.imgurdroid.events.BusProvider;
import com.nispok.imgurdroid.events.ImgurServiceEvents;
import com.nispok.imgurdroid.listeners.OnGalleryScrollListener;
import com.nispok.imgurdroid.models.Gallery;
import com.nispok.imgurdroid.models.GalleryInfo;
import com.nispok.imgurdroid.services.Imgur;

public class GalleryContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        OnGalleryScrollListener.GalleryScrollListener {

    private static final String TAG = GalleryContentFragment.class.getSimpleName();

    public static final String EXTRA_GALLERY_SECTION = "EXTRA_GALLERY_SECTION";

    private static final String SAVED_GALLERY_DATA = "SAVED_GALLERY_DATA";
    private static final String SAVED_GALLERY_INFO = "SAVED_GALLERY_INFO";

    private SwipeRefreshLayout galleryContainer;
    private GalleryAdapter galleryAdapter;

    private Gallery galleryData = new Gallery();
    private GalleryInfo galleryInfo = new GalleryInfo();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            loadValuesFromArguments(getArguments());
        }

        if (savedInstanceState != null) {
            galleryData = (Gallery) savedInstanceState.getSerializable(SAVED_GALLERY_DATA);
            galleryInfo = (GalleryInfo) savedInstanceState.getSerializable(SAVED_GALLERY_INFO);
        }

        View view = inflater.inflate(R.layout.fragment_gallery_content, container, false);

        loadViews(view);

        return view;
    }

    private void loadValuesFromArguments(@NonNull Bundle arguments) {
        galleryInfo.setSection(arguments.getString(EXTRA_GALLERY_SECTION));
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
        registerToBuses();
        if (shouldLoadGallery()) {
            loadGallery();
        } else {
            galleryAdapter.add(galleryData.getData());
        }
    }

    private void registerToBuses() {
        registerToSectionBus();
        registerToErrorBus();
    }

    private void registerToSectionBus() {
        BusProvider.bus(galleryInfo.getSection()).register(this);
    }

    private void registerToErrorBus() {
        BusProvider.bus(BusProvider.ERROR_BUS_KEY).register(this);
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
        unregisterFromBuses();
    }

    private void unregisterFromBuses() {
        unregisterFromSectionBus();
        unregisterFromErrorBus();
    }

    private void unregisterFromSectionBus() {
        BusProvider.bus(galleryInfo.getSection()).unregister(this);
    }

    private void unregisterFromErrorBus() {
        BusProvider.bus(BusProvider.ERROR_BUS_KEY).unregister(this);
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
