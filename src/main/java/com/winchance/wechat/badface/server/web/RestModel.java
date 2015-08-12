package com.winchance.wechat.badface.server.web;

import com.winchance.dataprotocol.Entity;
import com.winchance.dataprotocol.ErrorInfo;
import com.winchance.dataprotocol.Feed;

public class RestModel<T> extends com.winchance.dataprotocol.RestModel<T> {
    public RestModel() {}

    public RestModel(ErrorInfo errorInfo) {
        super("1.0", errorInfo);
    }

    public RestModel(Entity<T> entity, ErrorInfo errorInfo) {
        super("1.0", entity, errorInfo);
    }

    public RestModel(Feed<T> feed, ErrorInfo errorInfo) {
        super("1.0", feed, errorInfo);
    }

    public RestModel(Entity<T> entity, Feed<T> feed, ErrorInfo errorInfo) {
        super("1.0", entity, feed, errorInfo);
    }
}
