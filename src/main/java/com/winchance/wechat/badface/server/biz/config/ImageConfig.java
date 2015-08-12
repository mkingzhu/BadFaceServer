package com.winchance.wechat.badface.server.biz.config;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.winchance.wechat.badface.server.dao.ConfigDao;

public class ImageConfig {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final WriteLock writeLock = lock.writeLock();

    private final ReadLock readLock = lock.readLock();

    private String imageBaseDir = null;

    private String imageUrlPrefix = null;

    @Autowired
    private ConfigDao configDao;

    @PostConstruct
    public void reload() {
        writeLock.lock();
        try {
            imageBaseDir = configDao.get("IMAGE_BASE_DIR");
            imageUrlPrefix = configDao.get("IMAGE_URL_PREFIX");
        } finally {
            writeLock.unlock();
        }
    }

    public String getImageBaseDir() {
        readLock.lock();
        try {
            return imageBaseDir;
        } finally {
            readLock.unlock();
        }
    }

    public String getImageUrlPrefix() {
        readLock.lock();
        try {
            return imageUrlPrefix;
        } finally {
            readLock.unlock();
        }
    }
}
