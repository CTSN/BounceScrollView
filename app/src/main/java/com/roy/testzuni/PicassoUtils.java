package com.roy.testzuni;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2017/7/25.
 */

public class PicassoUtils {
    //单例模式
    private static PicassoUtils sInstance;
    private final Context mContext;

    public static PicassoUtils getsInstance(Context c) {
        if (sInstance == null) {
            sInstance = new PicassoUtils(c);
        }
        return sInstance;
    }

    private PicassoUtils(Context c) {
        mContext = c;
    }

    /**
     * 指定大小加载图片
     *
     * @param path       图片路径
     * @param width      宽
     * @param height     高
     * @param mImageView 控件
     */
    public void loadImageViewSize(String path, int width, int height, ImageView mImageView) {
    }


    /**
     * 加载有默认图片
     *
     * @param path       图片路径
     * @param resId      默认图片资源
     * @param mImageView 控件
     */
    public void loadImageViewHolder(String path, int resId, ImageView mImageView) {
        if (!"".equals(path)) {
            Picasso.with(mContext).load(path).fit().placeholder(resId).into(mImageView);
        }
    }
    /**
     * 加载有默认图片
     *
     * @param path       图片路径
     * @param mImageView 控件
     */
    public void loadImageViewHolder(String path, ImageView mImageView) {
        if (!"".equals(path)) {
            Picasso.with(mContext).load(path).fit().placeholder(R.drawable.default_iv).into(mImageView);
        }
    }

    /**
     * 加载无默认图片
     *
     * @param path       图片路径
     * @param mImageView 控件
     */
    public void loadImage(String path, ImageView mImageView) {
        if (!"".equals(path)) {
            Picasso.with(mContext).load(path).fit().into(mImageView);
        }
    }


    /**
     * 裁剪图片
     *
     * @param path       图片路径
     * @param mImageView 控件
     */
    public void loadImageViewCrop(String path, ImageView mImageView) {
        Picasso.with(mContext).load(path).transform(new CropImageView()).into(mImageView);
    }

    /**
     * 自定义图片裁剪
     */
    public class CropImageView implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);

            if (newBitmap != null) {
                //内存回收
                source.recycle();
            }
            return newBitmap;
        }

        @Override
        public String key() {
            return "lgl";
        }
    }
}
