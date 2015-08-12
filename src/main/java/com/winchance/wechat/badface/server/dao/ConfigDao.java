package com.winchance.wechat.badface.server.dao;

import org.springframework.dao.DataAccessException;

public interface ConfigDao {
    public String get(String key) throws DataAccessException;
}
