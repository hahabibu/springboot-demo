package com.noob.framework.di;

public class UserDaoImpl implements UserDao{
    public boolean save(String userName){
        System.out.println("mod save:" + userName);
        return true;
    }
}
