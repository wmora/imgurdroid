package com.nispok.imgurdroid.models;

import java.io.Serializable;
import java.util.List;

public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<GalleryResult> data;
    private boolean success;

    public List<GalleryResult> getData() {
        return data;
    }

    public void setData(List<GalleryResult> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
