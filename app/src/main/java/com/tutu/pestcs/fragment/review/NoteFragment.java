package com.tutu.pestcs.fragment.review;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.activity.CheakRecoderDetail;
import com.tutu.pestcs.app.ReviewDataCall;
import com.tutu.pestcs.app.TApplication;
import com.tutu.pestcs.base.BaseFragment;
import com.tutu.pestcs.bean.CheakInsertBean;
import com.tutu.pestcs.bean.NoteBean;
import com.tutu.pestcs.comfig.ActivityJumpParams;
import com.tutu.pestcs.db.NoteDao;
import com.tutu.pestcs.event.ModifyModeEvent;
import com.tutu.pestcs.event.SaveInsertEvent;
import com.tutu.pestcs.widget.OverScrollView;
import com.tutu.pestcs.widget.ToastUtils;
import com.tutu.pestcs.widget.TuLinearLayout;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tutu on 16/4/17.
 */
public class NoteFragment extends BaseFragment {
    @Bind(R.id.et_note)
    EditText et_note;
    @Bind(R.id.base_layout)
    OverScrollView baseLayout;
    @Bind(R.id.tbase)
    TuLinearLayout tbase;


    private String unitycode;
    private NoteBean bean = new NoteBean();


    @Override
    public int getLayoutID() {
        return R.layout.review_note_fragment;
    }

    @Override
    public void handleMessage(Message msg) {

    }


    @OnClick({R.id.btn_save,R.id.btn_exit,R.id.btn_photos})
    public void onClick(View v){

        CheakRecoderDetail cheakRecoderDetail =  (CheakRecoderDetail)getActivity();
        switch (v.getId()){
            case R.id.btn_save:
                RxBus.postEvent(new SaveInsertEvent(),SaveInsertEvent.class);
                cheakRecoderDetail.saveHeadData();
                break;
            case R.id.btn_exit:
                cheakRecoderDetail.deleteRecodeDialog();
                break;
            case R.id.btn_photos:
                cheakRecoderDetail.toPhotoActivity();
                break;
        }
    }

    @Override
    public void initView() {
        //tbase.setChildEnable(tbase, false);


        unitycode = getArguments().getString(ActivityJumpParams.UNITYCODE);
        if (unitycode == null) {
            ToastUtils.showToast("非法记录查询");
            return;
        }

        // TODO: 2016/6/18 查询蟑螂详情 根据unitycode
        Tasks.executeInBackground(getActivity(), new BackgroundWork<NoteBean>() {
            @Override
            public NoteBean doInBackground() throws Exception {
                return NoteDao.queryByUnitID(unitycode);
            }
        }, new Completion<NoteBean>() {
            @Override
            public void onSuccess(Context context, NoteBean result) {
                if (result == null) {
                    return;
                }
                bean = result;
                initReviewData();
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

        registModifyEvent();
    }


    private void registModifyEvent() {
        subscriptions.add(RxBus.obtainEvent(ModifyModeEvent.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ModifyModeEvent>() {
                    @Override
                    public void call(ModifyModeEvent Event) {
//                        if (Event.isEditable()) {
//                            tbase.setChildEnable(tbase, true);
//                        } else {
//                            tbase.setChildEnable(tbase, false);
//                            onSave();
//                        }
                        onSave();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }


    private void initReviewData() {
        et_note.setText(bean.getNote());
    }


    private void onSave() {

        if (veryfyInput()) {
            TApplication.noteBean = bean;
        } else {
            TApplication.noteBean = null;
        }
        TApplication.note = true;
        ReviewDataCall.saveReviewData(getActivity());
    }


    private boolean veryfyInput() {
        String note = et_note.getText().toString().trim();

        if (TextUtils.isEmpty(note)) {
            return false;
        }

        if (TextUtils.isEmpty(bean.getUnitCode())) {
            CheakInsertBean cheakInsertBean = ((CheakRecoderDetail) getActivity()).getCheakInsertBean();
            bean.setUnitCode(cheakInsertBean.getUnitCode());
            bean.setUniType(cheakInsertBean.getUnitClassID());
            bean.setTaskCode(cheakInsertBean.getTaskCode());
            bean.setAreaCode(cheakInsertBean.getAreaCode());
            bean.setKeyUnit(cheakInsertBean.isKeyUnit());
            bean.setExpertCode(cheakInsertBean.getExpertCode());
        }

        bean.setNote(note);

        return true;
    }


}
