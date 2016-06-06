package com.bing.developer;

/**
 * Created by ice on 2016/6/1.
 */
public class SelectedArticle {
    private int id;
    private String title;
    private int likeNUmber;
    private int commentNumber;
    private String avatarUrl;

    public SelectedArticle(int id, String title, int likeNUmber, int commentNumber, String avatarUrl) {
        this.id = id;
        this.title = title;
        this.likeNUmber = likeNUmber;
        this.commentNumber = commentNumber;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeNUmber() {
        return likeNUmber;
    }

    public void setLikeNUmber(int likeNUmber) {
        this.likeNUmber = likeNUmber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
