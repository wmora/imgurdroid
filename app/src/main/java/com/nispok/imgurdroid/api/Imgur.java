package com.nispok.imgurdroid.api;

import com.nispok.imgurdroid.models.GalleryResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Imgur {

    /**
     * Returns the images in the gallery. For example the main gallery is
     * <a href="https://api.imgur.com/3/gallery/hot/viral/0.json" />
     *
     * @param section   hot | top | user
     * @param sort      viral | top | time | rising (only available with user section)
     * @param page      integer - the data paging number
     * @param window    Change the date range of the request if the section is "top", day | week |
     *                  month | year | all
     * @param showViral true | false - Show or hide viral images from the 'user' section
     * @param callback  a {@link retrofit.Callback<com.nispok.imgurdroid.models.GalleryResult>}
     */
    @GET("/gallery/{section}/{sort}/{window}/{page}")
    void getUserPhoto(@Path("section") String section,
                      @Path("sort") String sort,
                      @Path("window") String window,
                      @Path("page") int page,
                      @Query("showViral") boolean showViral,
                      Callback<GalleryResult> callback);

}
