package com.roy.testzuni;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/6/11.
 */

public class BannerView extends FrameLayout {

    private Context mContext;
    private List<String> mDatas;
    private MyViewPager viewPager;
    private TextView tvSelect;
    private BannerViewAdapter mAdapter;
    private List<ImageView> dotList;
    private Handler mHandler;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
        initData();
    }

    void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_view, this, true);
        viewPager = (MyViewPager) view.findViewById(R.id.vp);
//        ll = (LinearLayout) view.findViewById(R.id.ll_dot);
        tvSelect = (TextView) view.findViewById(R.id.tv_banner_select);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int position = viewPager.getCurrentItem();
                viewPager.setCurrentItem(position +1);
                mHandler.sendEmptyMessageDelayed(0,4000);
            }
        };
    }

    private void initData() {
        mDatas = new ArrayList<>();
        dotList = new ArrayList<>();
    }


//    void initDotList() {
//        for (int i = 0; i < mDatas.size(); i++) {
//            ImageView dot = new ImageView(mContext);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dot.setImageResource(R.drawable.dot_selector);
//            params.setMargins(0, 0, 10, 0);
//            dot.setLayoutParams(params);
//            dotList.add(dot);
//            ll.addView(dot);
//
//        }
//    }

//    private void selectDot(int position) {
//        for (int i = 0; i < ll.getChildCount(); i++) {
//            if (i == position)
//                ll.getChildAt(i).setEnabled(false);
//            else
//                ll.getChildAt(i).setEnabled(true);
//        }
//    }

    public void setmDatas(final List<String> mDatas) {
        if (mDatas.size() == 0){
            return;
        }
        this.mDatas = mDatas;
//        initDotList();
        viewPager.setOffscreenPageLimit(mDatas.size());
        mAdapter = new BannerViewAdapter();
        viewPager.setAdapter(mAdapter);
//        selectDot(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPositon = position % mDatas.size();
                if (listener !=null){
                    listener.onSelect(realPositon);
                }
                tvSelect.setText((realPositon+1)+"/"+mDatas.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(100-100%mDatas.size());

//        startLoop();
    }

    class BannerViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mDatas.size() > 0 ? Integer.MAX_VALUE : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int realPosition = position % mDatas.size();
            View view = View.inflate(container.getContext(),R.layout.gallery_view_item,null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_photo);
            PicassoUtils.getsInstance(mContext).loadImageViewHolder(mDatas.get(realPosition),iv);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public void startLoop(){
        mHandler.sendEmptyMessageDelayed(0,4000);
    }

    public void stopLoop(){
        mHandler.removeCallbacksAndMessages(null);
    }

    private OnPageListener listener;

    public void addPageListener(OnPageListener listener) {
        this.listener = listener;
    }

    public interface OnPageListener {
        void onSelect(int position);
        void onClick(View view, int position);
    }
}
