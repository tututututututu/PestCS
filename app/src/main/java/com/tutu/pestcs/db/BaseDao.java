package com.tutu.pestcs.db;

/**
 * Created by 47066 on 2016/8/8.
 */
public abstract class BaseDao {
    public abstract void saveBindID();
    public abstract void saveOrUpdate();
    public abstract void update();
    public abstract void delete();
    public abstract void deleteByUnicode();
}
