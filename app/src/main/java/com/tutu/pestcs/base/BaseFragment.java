package com.tutu.pestcs.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.RequestManager;
import com.tutu.pestcs.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;


/**
 * @author tuhui
 * @Title:BaseFragment
 * @Description: fragment基类 处理子类的公共事件 注解
 * @Company:iblue
 * @date 2016-2-23下午1:14:20
 */
public abstract class BaseFragment extends Fragment {
    public abstract void handleMessage(Message msg);

    public abstract void initView();

    public abstract int getLayoutID();

    private InputMethodManager imm;
    protected Context mActivityContext;
    protected List<Subscription> subscriptions;


    private View rootView;
    protected RequestManager glide;
    protected SVProgressHUD svProgressHUD;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessage(msg);
        }
    };

    protected void sendMessage(Message msg) {
        mHandler.sendMessage(msg);
        handleMessage(msg);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new ArrayList<>();
        mActivityContext = getContext();
        svProgressHUD = ((BaseActivity) getActivity()).getSVProgressHUD();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutID(), null);
        ButterKnife.bind(this, rootView);
//		ViewGroup parent = (ViewGroup) rootView.getParent();
//		if (parent != null) {
//			parent.removeView(rootView);
//		}
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Nullable
    @OnClick(R.id.base_layout)
    public void onBaseClick(View view) {
        hideKeyBoard();
    }


    /**
     * 隐蔽软键盘
     */
    protected final void hideKeyBoard() {
        if (mActivityContext != null && (mActivityContext instanceof Activity)) {
            if (null == imm) {
                imm = (InputMethodManager) mActivityContext
                        .getApplicationContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
            }
            View currentFocus = ((Activity) mActivityContext).getCurrentFocus();
            if (null != currentFocus && imm.isActive()) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 显示软键盘
     */
    protected final void showKeyBoard(View focus) {
        if (mActivityContext != null && (mActivityContext instanceof Activity)) {
            if (null == imm) {
                imm = (InputMethodManager) mActivityContext
                        .getApplicationContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
            }
            if (null == focus) {
                focus = ((Activity) mActivityContext).getCurrentFocus();
            }
            if (null != focus) {
                imm.showSoftInput(focus, InputMethodManager.SHOW_FORCED);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);


    }
}
