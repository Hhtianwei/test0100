package com.training.springboot.test0100.repository;

import com.training.springboot.test0100.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 田伟 on 2018-08-20.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
