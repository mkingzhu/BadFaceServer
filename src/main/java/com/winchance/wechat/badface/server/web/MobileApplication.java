package com.winchance.wechat.badface.server.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.winchance.wechat.badface.server.web.resource.ImageFeedResource;

public class MobileApplication extends Application {
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/images", ImageFeedResource.class);

        return router;
    }
}
