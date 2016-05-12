package com.tutu.pestcs.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.tutu.pestcs.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by tutu on 16/4/10.
 */
public abstract class BaseActivity extends com.tutu.pestcs.base.AbsActivity {

    public abstract int getLayoutID();

    public abstract void handleMessage(Message msg);

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initData();

    protected Context mBaseActivityContext;
    protected Context mApplicationContext;
    public SVProgressHUD svProgressHUD;
    protected List<Subscription> subscriptions;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        subscriptions = new ArrayList<>();
        svProgressHUD = new SVProgressHUD(this);
        mBaseActivityContext = this;
        mApplicationContext = getApplicationContext();
        initView(savedInstanceState);
        initData();
    }

    public SVProgressHUD getSVProgressHUD() {
        return svProgressHUD;
    }

    @Nullable
    @OnClick(R.id.base_layout)
    public void onBaseClick(View view) {
        hideKeyBoard();
    }

    public void showLoadingDialog(String msg) {
        svProgressHUD.showWithStatus(msg, SVProgressHUD.SVProgressHUDMaskType.None);
    }

    protected void sendMessage(Message msg) {
        mHandler.sendMessage(msg);
        handleMessage(msg);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        hideKeyBoard();
        super.onDestroy();
        ButterKnife.unbind(this);
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }


    protected void showLoadingDialog(Context context) {

    }

    protected void cancelLoadingDialog() {

    }

}
