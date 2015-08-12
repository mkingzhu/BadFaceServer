package com.winchance.wechat.badface.server.web.resource;

import java.lang.reflect.Type;

import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.gson.reflect.TypeToken;
import com.winchance.dataprotocol.Entity;
import com.winchance.util.SpringContextUtil;
import com.winchance.util.StringUtil;
import com.winchance.util.convertor.GsonConvertor;
import com.winchance.web.JsonRepresentation;
import com.winchance.wechat.badface.server.biz.ImageBiz;
import com.winchance.wechat.badface.server.biz.config.ImageConfig;
import com.winchance.wechat.badface.server.model.Image;
import com.winchance.wechat.badface.server.model.ImagePostResultVo;
import com.winchance.wechat.badface.server.web.ErrorInfo;
import com.winchance.wechat.badface.server.web.RestModel;

public class ImageFeedResource extends ServerResource {
    private static final Type reqType = new TypeToken<RestModel<Image>>() {}.getType();

    private static final Type respType = new TypeToken<RestModel<ImagePostResultVo>>() {}.getType();

    private static final ImageBiz imageBiz = SpringContextUtil.getBean(ImageBiz.class);

    private static final ImageConfig imageConfig = SpringContextUtil.getBean(ImageConfig.class);

    @Override
    protected Representation post(Representation representation)
            throws ResourceException {
        Entity<ImagePostResultVo> entity = new Entity<ImagePostResultVo>();
        com.winchance.dataprotocol.ErrorInfo errorInfo = ErrorInfo.OK;

        do {
            // 解析输入
            RestModel<Image> restModel = null;
            try {
                restModel = GsonConvertor.<RestModel<Image>>fromJson(representation.getReader(), reqType);
            } catch (Exception ignore) {
                errorInfo = ErrorInfo.PARSE_PARAMS_ERROR;
                break;
            }

            // 判断输入是否为空
            if (null == restModel
                    || null == restModel.getEntity()
                    || null == restModel.getEntity().getModel()) {
                errorInfo = ErrorInfo.PARAMS_NOT_FULL;
                break;
            }
            Image image = restModel.getEntity().getModel();
            String userId = image.getUserId();
            Long referTime = image.getReferTime();
            String imageString = image.getImageString();
            if (StringUtil.isEmpty(userId)
                    || StringUtil.isEmpty(imageString)
                    || (null == referTime || 0L == referTime)) {
                errorInfo = ErrorInfo.PARAMS_NOT_FULL;
                break;
            }

            // 存储图片并插入数据
            String imageUrl = imageBiz.insert(image);
            if (null == imageUrl) {
                errorInfo = ErrorInfo.IO_EXCEPTION;
                break;
            }

            // 设置返回信息
            ImagePostResultVo imagePostResultVo = new ImagePostResultVo();
            imagePostResultVo.setUrl(imageConfig.getImageUrlPrefix() + imageUrl);
            entity.setModel(imagePostResultVo);
        } while (false);

        return new JsonRepresentation(new RestModel<ImagePostResultVo>(entity, errorInfo), respType);
    }
}
