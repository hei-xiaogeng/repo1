package com.gmc.lvyou.dao;

import com.gmc.lvyou.domain.User;

public interface UserDao {
   // User findByUsernameAndPassword(String username, String password);
   User findByUsernameAndPassword(String username, String password);
    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 注册保存
     * @param user
     */
    void save(User user);

    User findbycode(String code);

    void updateStatus(User user);
}
