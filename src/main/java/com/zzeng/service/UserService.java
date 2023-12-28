package com.zzeng.service;

import com.zzeng.po.User;

/**
 * @author DERK
 * @Version 1.0
 */
public interface UserService {

    User checkUser(String username,String password);
}
