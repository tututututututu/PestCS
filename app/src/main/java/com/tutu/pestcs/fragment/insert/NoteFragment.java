package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tutu.pestcs.R;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.NoteBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.NoteDao;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tutu on 16/4/17.
 */
public class NoteFragment extends BaseFragment {
    @Bind(R.id.et_note)
    EditText et_note;


    private CheakInsertBean cheakInsertBean;
    private NoteBean bean = new NoteBean();

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView() {
        cheakInsertBean = getArguments().getParcelable(ActivityJumpParams.CHEAK_INSERT_BEAN);
        bean.setUnitCode(cheakInsertBean.getUnitCode());
    }

    @Override
    public int getLayoutID() {
        return R.layout.note_fragment;
    }


    @OnClick({R.id.btn_exit, R.id.btn_save})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                getActivity().finish();
                break;
            case R.id.btn_save:
                saveData();
                break;
        }
    }

    private void saveData() {
        String note = et_note.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
            svProgressHUD.showErrorWithStatus("请输入内容");
        } else {
            //保存
            if (((InsertActivity) getActivity()).canSave()) {
                bean.setNote(note);
                NoteDao.saveOrUpdate(bean);
                ToastUtils.showToast("保存成功");
            } else {
                ToastUtils.showToast("请填写单位类型和地址,是否重点单位");
            }

        }
    }
}
