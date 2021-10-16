package com.gmc.lvyou.service.impl;

import com.gmc.lvyou.dao.UserDao;
import com.gmc.lvyou.dao.impl.UserDaoimpl;
import com.gmc.lvyou.domain.User;
import com.gmc.lvyou.service.Userservlet;
import com.gmc.lvyou.util.MailUtils;
import com.gmc.lvyou.util.UuidUtil;

public class Userservletimpl implements Userservlet {
    private UserDao userdao=new UserDaoimpl();
    private UserDao Userdao=new UserDaoimpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1根据用户名查找对象
        User u= userdao.findByUsername(user.getUsername());


        if (u!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());

        userdao.save(user);

        //3.激活邮件发送，邮件正文？

        String content="<a href='http://localhost/lvyou/activeUserServlet?code="+user.getCode()+"'>点一下激活陆游网</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 根据激活码激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {

        User user=userdao.findbycode(code);
        if (user!=null){
            userdao.updateStatus(user);
            return true;
        }else {
            return false;
        }


    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        return userdao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }


}
