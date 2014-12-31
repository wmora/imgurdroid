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

package com.nispok.imgurdroid.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nispok.imgurdroid.BuildConfig;
import com.nispok.imgurdroid.events.BusProvider;
import com.nispok.imgurdroid.events.ImgurServiceEvents;
import com.nispok.imgurdroid.models.Gallery;
import com.nispok.imgurdroid.models.GalleryInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Handles all interaction with <a href="https://api.imgur.com>Imgur's API</a>
 */
public class Imgur {

    public static class Section {
        public static final String HOT = "hot";
        public static final String TOP = "top";
        public static final String USER = "user";

        public static List<String> getSections() {
            return new ArrayList<>(Arrays.asList(HOT, TOP, USER));
        }
    }

    public static class Sort {
        public static final String VIRAL = "viral";
        public static final String TOP = "top";
        public static final String TIME = "time";
        public static final String RISING = "rising";

        public static List<String> getSorts() {
            return new ArrayList<>(Arrays.asList(VIRAL, TOP, TIME, RISING));
        }
    }

    public static class Window {
        public static final String DAY = "day";
        public static final String WEEK = "week";
        public static final String MONTH = "month";
        public static final String YEAR = "year";
        public static final String ALL = "all";

        public static List<String> getWindows() {
            return new ArrayList<>(Arrays.asList(DAY, WEEK, MONTH, YEAR, ALL));
        }
    }

    private static RestAdapter restAdapter;
    private static ImgurService service;

    private Imgur() {
    }

    /**
     * Endpoints
     */
    private interface ImgurService {
        @GET("/gallery/{section}/{sort}/{window}/{page}")
        void getGallery(@Path("section") String section,
                        @Path("sort") String sort,
                        @Path("window") String window,
                        @Path("page") int page,
                        @Query("showViral") boolean showViral,
                        ImgurCallback<Gallery> callback);
    }

    /**
     * All calls need, at minimum, the client_id for the registered app
     *
     * @see <a href="https://api.imgur.com/#register>Register an Application</a>
     */
    private static class ImgurRequestInterceptor implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Authorization", "Client-ID " + BuildConfig.IMGURDROID_CLIENT_ID);
        }
    }

    /**
     * Common {@link retrofit.Callback} for all
     * {@link com.nispok.imgurdroid.services.Imgur.ImgurService} calls. All request failures will
     * dispatch an {@link com.nispok.imgurdroid.events.ImgurServiceEvents.ErrorEvent}
     *
     * @param <T> the expected request response
     */
    private static abstract class ImgurCallback<T> implements Callback<T> {
        @Override
        public void success(T result, Response response) {
            success(result);
        }

        public abstract void success(T result);

        @Override
        public void failure(RetrofitError error) {
            BusProvider.bus(BusProvider.ERROR_BUS_KEY).post(
                    new ImgurServiceEvents.ErrorEvent(error));
        }
    }

    private static RestAdapter getRestAdapter() {

        if (restAdapter == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BuildConfig.IMGUR_API_BASE_URL)
                    .setRequestInterceptor(new ImgurRequestInterceptor())
                    .setLogLevel(BuildConfig.RETROFIT_LOGGING)
                    .setConverter(new GsonConverter(gson))
                    .build();
        }

        return restAdapter;
    }

    private static ImgurService getService() {

        if (service == null) {
            service = getRestAdapter().create(ImgurService.class);
        }

        return service;
    }

    /**
     * Returns the images in the gallery. For example the main gallery is
     * <a href="https://api.imgur.com/3/gallery/hot/viral/0.json" />
     *
     * @param section   hot | top | user. @see {@link Section}
     * @param sort      viral | top | time | rising (only available with user section). @see
     *                  {@link Sort}
     * @param window    Change the date range of the request if the section is "top", day | week |
     *                  month | year | all. @see {@link Window}
     * @param page      integer - the data paging number
     * @param showViral true | false - Show or hide viral images from the 'user' section.
     */
    private static void getGallery(final String section, String sort, String window, int page,
                                   boolean showViral) {
        getService().getGallery(section, sort, window, page, showViral,
                new ImgurCallback<Gallery>() {
                    @Override
                    public void success(Gallery result) {
                        BusProvider.bus(section).post(
                                new ImgurServiceEvents.GallerySuccessEvent(result));
                    }
                });
    }

    /**
     * Returns the images in the gallery. For example the main gallery is
     * <a href="https://api.imgur.com/3/gallery/hot/viral/0.json" />
     *
     * @param galleryInfo request info
     */
    public static void getGallery(GalleryInfo galleryInfo) {
        getGallery(galleryInfo.getSection(), galleryInfo.getSort(), galleryInfo.getWindow(),
                galleryInfo.getPage(), galleryInfo.showViral());
    }

    /**
     * Returns the images in the gallery. For example the main gallery is
     * <a href="https://api.imgur.com/3/gallery/hot/viral/0.json" />
     */
    public static void getGallery() {
        getGallery(new GalleryInfo());
    }

}
