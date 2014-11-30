package com.nispok.imgurdroid.events;

import com.nispok.imgurdroid.models.Gallery;

import retrofit.RetrofitError;

public class ImgurServiceEvents {

    public static class GallerySuccessEvent extends Event<Gallery> {
        public GallerySuccessEvent(Gallery result) {
            super(result);
        }
    }

    public static class ErrorEvent extends Event<RetrofitError> {
        public ErrorEvent(RetrofitError result) {
            super(result);
        }
    }

}
