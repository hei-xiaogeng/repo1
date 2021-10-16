package com.gmc.lvyou.dao.impl;

import com.gmc.lvyou.dao.UserDao;
import com.gmc.lvyou.domain.User;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoimpl implements UserDao {
    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }

        return user;

    }

    /**
     * 注册保存
     *
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User findbycode(String code) {
        User user = null;

        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
            System.out.println("findbycode出错了");

        }

        return user;

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }

        return user;
    }


    @Override
    public  void updateStatus(User user) {

        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }
}

