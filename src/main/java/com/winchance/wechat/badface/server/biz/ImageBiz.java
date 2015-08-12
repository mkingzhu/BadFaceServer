package com.winchance.wechat.badface.server.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.winchance.util.RandomUtil;
import com.winchance.wechat.badface.server.biz.config.ImageConfig;
import com.winchance.wechat.badface.server.dao.ImageDao;
import com.winchance.wechat.badface.server.dao.dataobject.ImageDo;
import com.winchance.wechat.badface.server.model.Image;

public class ImageBiz {
    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageConfig imageConfig;

    public String insert(Image image) {
        FileOutputStream out = null;
        try {
            Base64 base64 = new Base64();
            byte[] imageContent = base64.decode(image.getImageString());

            String fileName = image.getUserId() + RandomUtil.getRandomString(16) + ".png";
            out = new FileOutputStream(new File(imageConfig.getImageBaseDir(), fileName));
            out.write(imageContent);

            ImageDo imageDo = new ImageDo();
            imageDo.setUserId(image.getUserId());
            imageDo.setReferTime(image.getReferTime());
            imageDo.setUrl(fileName);
            imageDao.save(imageDo);

            return fileName;
        } catch (Exception e) {
            return null;
        } finally {
            if (null != out)
                try {
                    out.close();
                } catch (IOException ignore) {
                }
        }
    }
}
