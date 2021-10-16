package com.gmc.lvyou.service;

import com.gmc.lvyou.dao.UserDao;
import com.gmc.lvyou.dao.impl.UserDaoimpl;
import com.gmc.lvyou.domain.User;

public interface Userservlet {


    boolean regist(User user);

    boolean active(String code);
    User login(User user);

}
