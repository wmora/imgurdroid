package com.nispok.imgurdroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.managers.SettingsManager;

public class SettingsFragment extends Fragment {

    private CheckBox nsfwCheckbox;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nsfwCheckbox = (CheckBox) view.findViewById(R.id.nsfw_checkbox);
        nsfwCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setNsfwEnabled(isChecked);
                loadNsfwSetting();
            }
        });
        loadNsfwSetting();
    }

    private void loadNsfwSetting() {
        nsfwCheckbox.setChecked(SettingsManager.isNsfwEnabled());
    }
}
