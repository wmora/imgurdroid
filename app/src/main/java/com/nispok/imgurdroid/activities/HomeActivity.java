package com.nispok.imgurdroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nispok.imgurdroid.R;
import com.nispok.imgurdroid.fragments.HomeFragment;
import com.nispok.imgurdroid.intents.RequestCodes;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_settings) {
            goToSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToSettings() {
        startActivityForResult(new Intent(getApplicationContext(), SettingsActivity.class),
                RequestCodes.SETTINGS_ACTIVITY);
    }
}
