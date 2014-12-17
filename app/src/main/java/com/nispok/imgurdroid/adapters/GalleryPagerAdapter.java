package com.nispok.imgurdroid.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nispok.imgurdroid.fragments.GalleryContentFragment;
import com.nispok.imgurdroid.services.Imgur;

public class GalleryPagerAdapter extends FragmentPagerAdapter {

    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new GalleryContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GalleryContentFragment.EXTRA_GALLERY_SECTION,
                Imgur.Section.getSections().get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return Imgur.Section.getSections().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Imgur.Section.getSections().get(position);
    }

}
