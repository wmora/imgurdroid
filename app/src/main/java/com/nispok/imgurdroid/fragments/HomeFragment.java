package com.nispok.imgurdroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halfbit.tinybus.Subscribe;
import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.events.BusProvider;
import com.nispok.imgurdroid.events.ImgurServiceEvents;
import com.nispok.imgurdroid.models.Gallery;
import com.nispok.imgurdroid.services.Imgur;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final String SAVED_GALLERY = "SAVED_GALLERY";

    private Gallery gallery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            gallery = (Gallery) savedInstanceState.getSerializable(SAVED_GALLERY);
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
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
        return gallery == null || gallery.getData().isEmpty();
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
        outState.putSerializable(SAVED_GALLERY, gallery);
    }

    @Subscribe
    public void onEvent(ImgurServiceEvents.GallerySuccessEvent event) {
        gallery = event.getResult();
    }

    @Subscribe
    public void onError(ImgurServiceEvents.ErrorEvent event) {
        Log.d(TAG, event.getResult().toString());
    }
}
