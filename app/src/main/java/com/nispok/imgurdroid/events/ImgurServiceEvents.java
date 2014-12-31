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

package com.nispok.imgurdroid.events;

import com.nispok.imgurdroid.models.Gallery;

import retrofit.RetrofitError;

/**
 * {@link com.nispok.imgurdroid.events.Event}s used in this app
 */
public class ImgurServiceEvents {

    /**
     * {@link com.nispok.imgurdroid.events.Event} used when gallery results are available
     */
    public static class GallerySuccessEvent extends Event<Gallery> {
        public GallerySuccessEvent(Gallery result) {
            super(result);
        }
    }

    /**
     * {@link com.nispok.imgurdroid.events.Event} used when a service error has occurred
     */
    public static class ErrorEvent extends Event<RetrofitError> {
        public ErrorEvent(RetrofitError result) {
            super(result);
        }
    }

}
