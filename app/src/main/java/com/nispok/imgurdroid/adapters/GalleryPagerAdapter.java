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
