package com.tutu.pestcs.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseActivity;
import com.tutu.pestcs.db.DBHelper;
import com.tutu.pestcs.utils.PhotosStore;
import com.tutu.pestcs.utils.StorageUtils;
import com.tutu.pestcs.widget.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import butterknife.Bind;
import butterknife.OnClick;

public class DataManageActivitay extends BaseActivity {

    @Bind(R.id.rl_tosd)
    RelativeLayout rlTosd;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.rl_open)
    RelativeLayout rlOpen;
    @Bind(R.id.rl_send)
    RelativeLayout rlSend;
    @Bind(R.id.rl_clear)
    RelativeLayout rlClear;

    private boolean isSend = false;

    @Override
    public int getLayoutID() {
        return R.layout.activity_data_manage_activitay;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_tosd, R.id.ll_back, R.id.rl_send, R.id.rl_clear,R.id.rl_open})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_tosd:
                isSend = false;
                CopyFile();
                break;
            case R.id.rl_send:
                isSend = true;
                CopyFile();
                break;
            case R.id.rl_clear:
                clearData1("清空数据前请将全部数据导出到你的电脑。\n" +
                        "确定清空全部现场检查数据吗？");
                break;
            case R.id.rl_open:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromFile(new File(PhotosStore.getPhotosDir()));
                intent.setDataAndType(uri, "image/*");
                startActivity(intent);
            break;
        }
    }

    private void clearData1(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("警告!");
        builder.setMessage(msg);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                clearData2("数据清空后不可恢复！\n" +
                        "是否清空全部现场检查数据");
            }
        });
        builder.create().show();
    }

    private void clearData2(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("警告!");
        builder.setMessage(msg);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllData();
            }
        });
        builder.create().show();
    }

    private void clearAllData() {
        svProgressHUD.showWithStatus("删除中");
        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                DBHelper.dropDB();
                return null;
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
                svProgressHUD.dismissImmediately();
            }

            @Override
            public void onError(Context context, Exception e) {
                svProgressHUD.dismissImmediately();
            }
        });


    }

    private void CopyFile() {
        svProgressHUD.showWithStatus("导出中...");
        Tasks.executeInBackground(this, new BackgroundWork<Void>() {
            @Override
            public Void doInBackground() throws Exception {
//                copy(StorageUtils.getFilesDir(TApplication.getInstance()).getAbsolutePath() + "/db.db", Environment
//                        .getExternalStorageDirectory().getAbsolutePath());
                copyDB(StorageUtils.getFilesDir(TApplication.getInstance()).getAbsolutePath() + "/db.db");
                return null;
            }
        }, new Completion<Void>() {
            @Override
            public void onSuccess(Context context, Void result) {
                svProgressHUD.dismissImmediately();
                if (isSend) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tutu/db/db.db");
                    if (!file.exists()) {
                        ToastUtils.showToast("文件不存在");
                        return;
                    }
                    share.putExtra(Intent.EXTRA_STREAM,
                            Uri.fromFile(file));
                    share.setType("*/*");//此处可发送多种文件
                    startActivity(Intent.createChooser(share, "Share"));
                }
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });
    }


    public void copyDB(String src) {
        if (!isExternalStorageWritable()) {
            ToastUtils.showToast("SD卡不可用");
            return;
        }

        File f = new File(src); //比如  "/data/data/com.hello/databases/test.db"

        File destDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                + "tutu/db");
        if (!destDir.exists()) {
            if (!destDir.mkdirs()) {
                ToastUtils.showToast("请给程序读取SD卡权限");
            }

        }

        String sdcardPath = Environment.getExternalStorageDirectory().getPath() + "/tutu/db/db.db";

        File o = new File(sdcardPath); //sdcard上的目标地址

        if (f.exists()) {

            FileChannel outF;

            try {

                outF = new FileOutputStream(o).getChannel();

                new FileInputStream(f).getChannel().transferTo(0, f.length(), outF);

            } catch (FileNotFoundException e) {

                e.printStackTrace();
                ToastUtils.showToast("导出失败 " + e.toString());
                return;
            } catch (IOException e) {

                e.printStackTrace();
                ToastUtils.showToast("导出失败" + e.toString());
                return;
            }

            ToastUtils.showToast("导出成功");

        }
    }


    public int copy(String fromFile, String toFile) {
        //要复制的文件目录
        File root = new File(fromFile);
        if (!root.exists()) {
            return -1;
        }

        //目标目录
        File targetDir = new File(toFile + "/db.db");
        if (targetDir.exists()) {
            targetDir.delete();
        }


        CopySdcardFile(fromFile, toFile + "/db.db");

        return 0;
    }

    //文件拷贝
    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    public int CopySdcardFile(String fromFile, String toFile) {

        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            ToastUtils.showToast("导出成功");
            return 0;

        } catch (Exception ex) {
            ToastUtils.showToast("导出失败");
            return -1;
        }

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
