package com.tutu.pestcs.db;

import com.tutu.pestcs.bean.User;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by tutu on 16/4/21.
 */
public class UserDao {

    public static void addUser(User user) throws DbException {

        DBHelper.getDBManager().save(user);

    }

    public static void saveOrUpdate(User user) {
        try {
            DBHelper.getDBManager().saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> queryAll() {
        try {
            return DBHelper.getDBManager().findAll(User.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询指定权限用户
    public static List<User> queryUser(String permisson) {
        try {
            return DBHelper.getDBManager().selector(User.class).where("UserGrade", "=", permisson).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(User user) {
        try {
            DBHelper.getDBManager().delete(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static User queryByName(String userName) {
        try {
            User user1 = DBHelper.getDBManager().selector(User.class).where("UserName", "=", userName)
                    .findFirst();
            return user1;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


}
