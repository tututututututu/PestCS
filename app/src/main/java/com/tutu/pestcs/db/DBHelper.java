package com.tutu.pestcs.db;

import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.utils.StorageUtils;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * Created by tutu on 16/4/20.
 */
public class DBHelper {
    public static DbManager getDBManager() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                // 数据库的名字
                .setDbName("db.db")
                // 保存到指定路径
                .setDbDir(StorageUtils.getFilesDir(TApplication.getInstance()))
                // 数据库的版本号
                .setDbVersion(1)
                // 数据库版本更新监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager arg0, int arg1, int arg2) {
                        LogUtil.e("数据库版本更新了！");
                    }
                });
        LogUtil.e("db path=" + daoConfig.getDbDir());
        DbManager manager = x.getDb(daoConfig);

        return manager;
    }
}
