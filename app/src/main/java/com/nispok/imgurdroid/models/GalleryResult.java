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
import java.util.List;

import static com.nispok.imgurdroid.managers.SettingsManager.isNsfwEnabled;

/**
 * A {@link com.nispok.imgurdroid.models.GalleryResult} can be either a
 * <a href="https://api.imgur.com/models/gallery_image">Gallery Image</a> or a
 * <a href="https://api.imgur.com/models/gallery_album">Gallery Album</a>
 */
public class GalleryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String description;
    private long datetime;
    private String type;
    private boolean animated;
    private int width;
    private int height;
    private int size;
    private long views;
    private long bandwidth;
    private String deletehash;
    private String link;
    private String gifv;
    private String mp4;
    private String webm;
    private boolean looping;
    private String vote;
    private boolean favorite;
    private boolean nsfw;
    private String section;
    private String accountUrl;
    private String accountId;
    private int ups;
    private int downs;
    private int score;
    private boolean isAlbum;
    private String cover;
    private int coverWidth;
    private int coverHeight;
    private String privacy;
    private String layout;
    private int imagesCount;
    private List<GalleryResult> images;

    /**
     * @return The ID for the image or album
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The title of the image or album.
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Description of the image or album.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Time inserted into the gallery, epoch time
     */
    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    /**
     * @return Image MIME type.
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return is the image animated
     */
    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    /**
     * @return The width of the image in pixels
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return The height of the image in pixels
     */
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The size of the image in bytes
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return The number of image views
     */
    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    /**
     * @return Bandwidth consumed by the image in bytes
     */
    public long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * @return the deletehash, if you're logged in as the image owner
     */
    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    /**
     * @return The direct link to the the image or album. (Note: if fetching an animated GIF that
     * was over 20MB in original size, a .gif thumbnail will be returned)
     */
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The .gifv link. Only available if the image is animated and type is 'image/gif'.
     */
    public String getGifv() {
        return gifv;
    }

    public void setGifv(String gifv) {
        this.gifv = gifv;
    }

    /**
     * @return The direct link to the .mp4. Only available if the image is animated and type is
     * 'image/gif'.
     */
    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    /**
     * @return The direct link to the .webm. Only available if the image is animated and type is
     * 'image/gif'.
     */
    public String getWebm() {
        return webm;
    }

    public void setWebm(String webm) {
        this.webm = webm;
    }

    /**
     * @return Whether the image has a looping animation. Only available if the image is animated
     * and type is 'image/gif'.
     */
    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    /**
     * @return The current user's vote on the album. null if not signed in or if the user hasn't
     * voted on it.
     */
    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    /**
     * @return Indicates if the current user favorited the image. Defaults to false if not signed in
     */
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * @return Indicates if the image has been marked as nsfw or not. Defaults to null if
     * information is not available.
     */
    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    /**
     * @return If the image has been categorized by our backend then this will contain the section
     * the image belongs in. (funny, cats, adviceanimals, wtf, etc)
     */
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return The username of the account that uploaded it, or null.
     */
    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    /**
     * @return The account ID of the account that uploaded it, or null.
     */
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return Upvotes for the image
     */
    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    /**
     * @return Number of downvotes for the image
     */
    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    /**
     * @return Imgur popularity score
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return if it's an album or not
     */
    public boolean isAlbum() {
        return isAlbum;
    }

    public void setAlbum(boolean isAlbum) {
        this.isAlbum = isAlbum;
    }

    /**
     * @return if it's an album, the ID of the album cover image
     * @see #isAlbum()
     */
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * @return if it's an album, the width, in pixels, of the album cover image
     * @see #isAlbum()
     */
    public int getCoverWidth() {
        return coverWidth;
    }

    public void setCoverWidth(int coverWidth) {
        this.coverWidth = coverWidth;
    }

    /**
     * @return if it's an album, the height, in pixels, of the album cover image
     * @see #isAlbum()
     */
    public int getCoverHeight() {
        return coverHeight;
    }

    public void setCoverHeight(int coverHeight) {
        this.coverHeight = coverHeight;
    }

    /**
     * @return if it's an album, the privacy level of the album, you can only view public if not logged in
     * as album owner
     * @see #isAlbum()
     */
    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    /**
     * @return if it's an album, the view layout of the album.
     * @see #isAlbum()
     */
    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    /**
     * @return if it's an album, the total number of images in the album
     * @see #isAlbum()
     */
    public int getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    /**
     * @return if it's an album, an array of all the images in the album (only available when requesting the
     * direct album)
     * @see #isAlbum()
     */
    public List<GalleryResult> getImages() {
        return images;
    }

    public void setImages(List<GalleryResult> images) {
        this.images = images;
    }

    public String getImageUrl() {
        String imageId = isAlbum ? cover : id;
        return "http://i.imgur.com/" + imageId + "l.jpg";
    }

    public int getImageHeight() {
        int imageHeight = isAlbum ? coverHeight : height;
        return imageHeight <= 640 ? imageHeight : 640;
    }

    /**
     * @return whether the content is NSFW and the app settings disabled this type of content
     * @see com.nispok.imgurdroid.managers.SettingsManager#isNsfwEnabled()
     */
    public boolean shouldDisplayNsfwWarning() {
        return nsfw && !isNsfwEnabled();
    }
}
