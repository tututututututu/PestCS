package com.tutu.pestcs.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.tutu.pestcs.R;
import com.tutu.pestcs.RxBus.RxBus;
import com.tutu.pestcs.bean.PhotoBean;
import com.tutu.pestcs.db.PhotoDao;
import com.tutu.pestcs.event.PhotoChangeEvent;
import com.tutu.pestcs.utils.PhotosStore;
import com.tutu.pestcs.widget.PhotoViewPager;
import com.tutu.pestcs.widget.ToastUtils;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;


public class ImagePagerActivity extends Activity {
    public static final String INTENT_IMAGESIZE = "imagesize";
    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    private List<View> guideViewList = new ArrayList<>();
    private LinearLayout guideGroup;
    private ArrayList<PhotoBean> imgUrls;
    private PhotoViewPager viewPager;
    private ImageAdapter mAdapter;


    public static void startImagePagerActivity(Context context, List<PhotoBean> imgUrls, int position) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putParcelableArrayListExtra(INTENT_IMGURLS, new ArrayList<>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepager);
        viewPager = (PhotoViewPager) findViewById(R.id.pager);
        View rl_cancel = findViewById(R.id.rl_cancel);

        guideGroup = (LinearLayout) findViewById(R.id.guideGroup);
        int startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        imgUrls = getIntent().getParcelableArrayListExtra(INTENT_IMGURLS);

        mAdapter = new ImageAdapter(this);
        mAdapter.setDatas(imgUrls);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideViewList.size(); i++) {
                    guideViewList.get(i).setSelected(i == position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(startPos);
        addGuideView(guideGroup, startPos, imgUrls);


        rl_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImagePagerActivity.this);
                builder.setTitle("提示")
                        .setMessage("删除这张照片吗?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO: 2016/6/25  删除照片

                                Tasks.executeInBackground(ImagePagerActivity.this, new BackgroundWork<String>() {
                                    @Override
                                    public String doInBackground() throws Exception {
                                        PhotoDao.delete(imgUrls.get(viewPager.getCurrentItem()));
                                        return null;
                                    }
                                }, new Completion<String>() {
                                    @Override
                                    public void onSuccess(Context context, String result) {
                                        ToastUtils.showToast("删除成功");
                                        RxBus.postEvent(new PhotoChangeEvent(),PhotoChangeEvent.class);
                                        imgUrls.remove(viewPager.getCurrentItem());
                                        mAdapter = new ImageAdapter(ImagePagerActivity.this);
                                        mAdapter.setDatas(imgUrls);
                                        viewPager.setAdapter(mAdapter);
                                        guideGroup.removeAllViews();
                                        addGuideView(guideGroup, 0, imgUrls);
                                    }

                                    @Override
                                    public void onError(Context context, Exception e) {

                                    }
                                });


                            }
                        });
                builder.create().show();

            }
        });
    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<PhotoBean> imgUrls) {
        if (imgUrls != null && imgUrls.size() > 0) {
            guideViewList.clear();
            for (int i = 0; i < imgUrls.size(); i++) {
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i == startPos ? true : false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources()
                        .getDimensionPixelSize(R.dimen.gudieview_width),
                        getResources().getDimensionPixelSize(R.dimen.gudieview_heigh));
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    private static class ImageAdapter extends PagerAdapter {

        private List<PhotoBean> datas = new ArrayList<>();
        private LayoutInflater inflater;
        private Context context;

        public void setDatas(List<PhotoBean> datas) {
            if (datas != null)
                this.datas = datas;
        }

        public ImageAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (datas == null) return 0;
            return datas.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if (view != null) {
                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);

                String imgurl = datas.get(position).getPictureName();

                File file = new File(PhotosStore.getPhotosDir() + imgurl);
                LogUtil.e(file.getAbsolutePath());
                LogUtil.e("file exist=" + file.exists());

                Glide.with(context).load(new File(PhotosStore.getPhotosDir() + imgurl)).into
                        (imageView);

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }


}
