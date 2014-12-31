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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds information about an image gallery
 */
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<GalleryResult> data = new ArrayList<>();
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
