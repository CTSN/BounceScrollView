package com.roy.testzuni;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;


/**
 * 阻尼上拉回弹控件
 * Created by roy on 2018/1/6.
 */
public class BounceScrollView extends ScrollView {
    private int DAMP_OFFSET = 6;    //阻尼系数 越大阻尼效果越大
    private Callback mCallback;
    private View mView;
    private Rect mRect = new Rect();
    private boolean isFirst = true;

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0)
            mView = getChildAt(0);  //获得子空间
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mView != null) {
            commonOnTouch(ev);
        }
        return super.onTouchEvent(ev);
    }

    private int tempY;  //记录差值
    private void commonOnTouch(MotionEvent ev) {
        int action = ev.getAction();
        int cy = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceY = cy - tempY;
                if (isFirst) {   //排除刚滑动无法获得滑动距离
                    distanceY = 0;
                    isFirst = false;
                }
                tempY = cy;
                if (isNeedMove()) {
                    if (mRect.isEmpty()) {  //记录原本位置
                        mRect.set(mView.getLeft(), mView.getTop(),
                                mView.getRight(), mView.getBottom());
                    }
                    mView.layout(mView.getLeft(), mView.getTop() + 2 * distanceY / DAMP_OFFSET,
                            mView.getRight(), mView.getBottom() + 2 * distanceY / DAMP_OFFSET);   //移动位置
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mRect.isEmpty()) {
                    if (Math.abs(mView.getTop()) > 250){
                        mCallback.callback(Math.abs(mView.getTop()));
                    }
                    resetPosition();
                }
                break;
        }
    }

    /**
     * 开启回弹动画
     */
    private void resetPosition() {
        Animation animation = new TranslateAnimation(0, 0, mView.getTop(),
                mRect.top);
        animation.setDuration(200);
        animation.setFillAfter(true);
        mView.startAnimation(animation);
        mView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        mRect.setEmpty();
        isFirst = true;
    }

    /**
     * 判断能否往下移动
     * @return
     */
    public boolean isNeedMove() {
        int offset = mView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // scrollY == 0是顶部
        // scrollY == offset是底部
        if (scrollY == offset) {
            return true;
        }
        return false;
    }

    public void setCallBack(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void callback(int top);
    }
}
