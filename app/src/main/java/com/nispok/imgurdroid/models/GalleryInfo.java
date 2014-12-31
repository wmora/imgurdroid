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
