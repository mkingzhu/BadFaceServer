package com.winchance.wechat.badface.server.dao.dataobject;

public class ImageDo {
    private Long id;

    private String userId;

    private Long referTime;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
