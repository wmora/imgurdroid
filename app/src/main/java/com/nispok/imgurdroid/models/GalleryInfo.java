package com.nispok.imgurdroid.models;

import com.nispok.imgurdroid.services.Imgur;

import java.io.Serializable;

public class GalleryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String section;
    private String sort;
    private String window;
    private int page;
    private boolean showViral;

    public GalleryInfo() {
        section = Imgur.Section.HOT;
        sort = Imgur.Sort.VIRAL;
        window = Imgur.Window.DAY;
        page = 0;
        showViral = true;
    }

    /**
     * @return hot | top | user. @see {@link com.nispok.imgurdroid.services.Imgur.Section}
     */
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return viral | top | time | rising (only available with user section). @see
     * {@link com.nispok.imgurdroid.services.Imgur.Sort}
     */
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the date range of the request if the section is "top", day | week | month |
     * year | all. @see {@link com.nispok.imgurdroid.services.Imgur.Window}
     */
    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    /**
     * @return the data paging number
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return true | false - Show or hide viral images from the 'user' section.
     */
    public boolean showViral() {
        return showViral;
    }

    public void setShowViral(boolean showViral) {
        this.showViral = showViral;
    }
}
