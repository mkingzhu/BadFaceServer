package com.winchance.wechat.badface.server.model;

public class Image {
    private String userId;

    private Long referTime;

    private String imageString;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getReferTime() {
        return referTime;
    }

    public void setReferTime(Long referTime) {
        this.referTime = referTime;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
