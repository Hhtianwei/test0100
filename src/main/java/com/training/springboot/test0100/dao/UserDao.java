package com.training.springboot.test0100.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 田伟 on 2018-08-20.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int setRole(int userId,int roleId){
        String sql = "INSERT INTO user_role(userId,roleId) VALUES (?,?)";
        return jdbcTemplate.update(sql,new Object[]{userId,roleId});
    }

}
