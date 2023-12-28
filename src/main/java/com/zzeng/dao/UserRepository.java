package com.zzeng.dao;

import com.zzeng.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DERK
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
