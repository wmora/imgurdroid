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

package com.nispok.imgurdroid.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.nispok.imgurdroid.R;

import static com.nispok.imgurdroid.Imgurdroid.getApplication;
import static com.nispok.imgurdroid.utils.ResourcesUtils.getString;

public class SettingsManager {

    private static SharedPreferences settingsPreferences;

    private static SharedPreferences getSettingsPreferences() {
        if (settingsPreferences == null) {
            settingsPreferences = getApplication().getSharedPreferences(
                    getString(R.string.settings_preferences_key), Context.MODE_PRIVATE);
        }

        return settingsPreferences;
    }

    private static SharedPreferences.Editor getEditor() {
        return getSettingsPreferences().edit();
    }

    /**
     * @return if NSFW is enabled for this app. Defaults to true
     */
    public static boolean isNsfwEnabled() {
        return getSettingsPreferences().getBoolean(getString(R.string.settings_nsfw_enabled_key),
                true);
    }

    /**
     * Changes NSFW setting for the app
     * @param nsfwEnabled true if NSFW should be enabled
     */
    public static void setNsfwEnabled(boolean nsfwEnabled) {
        getEditor().putBoolean(getString(R.string.settings_nsfw_enabled_key), nsfwEnabled).commit();
    }

}
