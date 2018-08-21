package com.training.springboot.test0100.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 田伟 on 2018-08-13.
 */
@Slf4j
@Component
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        String sql = "select username,password,enabled from users where username = ?";
        List<User> users = jdbcTemplate.query(sql, new String[]{userName}, (resultSet, i) -> {
            String name = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean enabled = resultSet.getBoolean("enabled");
            User user = new User(name,password,enabled,true,true,true, AuthorityUtils.NO_AUTHORITIES);
            return user;
        });

        if(users == null || users.size() == 0){
            throw new UsernameNotFoundException(String.format("Username %s not found",userName));
        }

        User user = users.get(0);

        List<GrantedAuthority> roleList = this.loadUserAuthorities(userName);
        if (roleList == null || roleList.size() == 0) {
            throw new UsernameNotFoundException(String.format("User %s has no GrantedAuthority",userName));
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.addAll(roleList);
        List<GrantedAuthority> resultRoles = new ArrayList<>();
        resultRoles.addAll(roles);

        return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,resultRoles);
    }

    protected List<GrantedAuthority> loadUserAuthorities(String userName) {
        String authoritiesByUsernameQuery = "SELECT R.role FROM USERS U  LEFT JOIN user_role UR ON UR.userId = U.id LEFT JOIN ROLES R ON UR.roleId = R.ID WHERE U.username = ?";
        return jdbcTemplate.query(authoritiesByUsernameQuery, new String[]{userName}, new RowMapper<GrantedAuthority>() {
            public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                String roleName = "ROLE_" + rs.getString("role");
                return new SimpleGrantedAuthority(roleName);
            }
        });
    }

}
