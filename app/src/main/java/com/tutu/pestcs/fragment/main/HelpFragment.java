package com.tutu.pestcs.fragment.main;

import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.tutu.pestcs.R;
import com.tutu.pestcs.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/7.
 */
public class HelpFragment extends BaseFragment {
    @Bind(R.id.rl_guobiao)
    RelativeLayout rlGuobiao;
    @Bind(R.id.rl_use_notice)
    RelativeLayout rlUseNotice;
    @Bind(R.id.rl_contact)
    RelativeLayout rlContact;

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.help_fragment;
    }


    @OnClick({R.id.rl_guobiao,R.id.rl_use_notice,R.id.rl_contact})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_contact:
                break;
            case R.id.rl_guobiao:
                break;
            case R.id.rl_use_notice:
                break;
        }
    }
}
