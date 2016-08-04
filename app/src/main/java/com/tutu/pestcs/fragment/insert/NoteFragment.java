package com.tutu.pestcs.fragment.insert;

import android.os.Message;
import android.text.TextUtils;
import android.widget.EditText;

import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.InsertActivity;
import com.tutu.pestcs.app.ReviewDataCall;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.NoteBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.event.SaveInsertEvent;
import com.tutu.pestcs.widget.ToastUtils;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
        bean.setUniType(cheakInsertBean.getUnitClassID());
        bean.setAreaCode(cheakInsertBean.getAreaCode());
        bean.setTaskCode(cheakInsertBean.getTaskCode());
        bean.setKeyUnit(cheakInsertBean.isKeyUnit());
        bean.setExpertCode(cheakInsertBean.getExpertCode());
        initSaveEvent();
    }


    private void initSaveEvent() {
        subscriptions.add(RxBus.obtainEvent(SaveInsertEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SaveInsertEvent>() {
                    @Override
                    public void call(SaveInsertEvent saveInsertEvent) {
                        saveEventData();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                })

        );
    }

    @Override
    public int getLayoutID() {
        return R.layout.note_fragment;
    }


    private void saveEventData(){
        if (((InsertActivity) getActivity()).canSave()) {

        }else {
            ToastUtils.showErrorToast("请填写检查单位名称或地点");
            return;
        }

        if (veryfyInput()) {
            TApplication.noteBeanI = bean;
        } else {
            TApplication.noteBeanI = null;
        }
        TApplication.note = true;
        ReviewDataCall.saveInserData(getActivity());
    }

    private boolean veryfyInput() {
        String note = et_note.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
           return false;
        }else {
            bean.setNote(note);
            return true;
        }
    }
}
