package com.nispok.imgurdroid.services;

import com.nispok.imgurdroid.models.Gallery;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

interface Imgur {

    @GET("/gallery/{section}/{sort}/{window}/{page}")
    void getGallery(@Path("section") String section,
                    @Path("sort") String sort,
                    @Path("window") String window,
                    @Path("page") int page,
                    @Query("showViral") boolean showViral,
                    Callback<Gallery> callback);

}
