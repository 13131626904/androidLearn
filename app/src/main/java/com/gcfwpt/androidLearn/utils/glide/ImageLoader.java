package com.gcfwpt.androidLearn.utils.glide;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.base.BaseApplication;

/**
 * Created by LH on 2017/10/30.
 */

public class ImageLoader {

    /**
     * 获取全局(Application)的Context
     * 生命周期为Application的生命周期
     * @return
     */
    private static Context getGlobalContext() {
        return BaseApplication.getApplication();
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(ImageView imageView, String imageUrl) {
        loadImage(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 加载图片，在加载过程中会显示占位图
     * @param imageView ImageView
     * @param imageUrl 图片地址
     */
    public static void loadImageWithPlaceHolder(ImageView imageView, String imageUrl) {
        loadImageWithPlaceHolder(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 加载图片，在加载过程中会显示占位图，失败也会显示占位图
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageView  ImageView
     * @param imageUrl  图片地址
     */
    public static void loadCircleImage(ImageView imageView, String imageUrl) {
        loadCircleImage(getGlobalContext(), imageView, imageUrl);
    }

    /**
     * 加载圆形图片
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     */
    public static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .transform(new CircleImageTransform(getGlobalContext()))
                .into(imageView);
    }

    /**
     * 加载Gif图
     * @param context    Context
     * @param imageView  ImageView
     * @param gifUrl     gif图地址
     */
    public static void loadGif(Context context, ImageView imageView, String gifUrl) {
        Glide.with(context)
                .load(gifUrl)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 根据特定的宽高加载图片
     * @param context    Context
     * @param imageView  ImageView
     * @param imageUrl   图片地址
     * @param width      图片的宽度
     * @param height     图片的高度
     */
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int width, int height) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .override(width, height)
                .into(imageView);
    }

    /**
     * 加载图片并设置合适大小
     *
     * @param context
     * @param picPath
     * @param imageView
     */
    public static void loadWithCrop(Context context, String picPath, ImageView imageView) {
        Glide.with(context)
                .load(picPath)
                .asBitmap()
                .centerCrop()
                .into(new MyBitmapImageViewTarget(imageView));
    }

    /**
     * 横向充满纵向自适应
     * author LH
     * create at 2017/11/3 10:47
     */
    public static void loadLanFull(Context context, int id, final ImageView mImgView){
        Glide.with(context).load(id).error(R.drawable.ic_default).diskCacheStrategy(DiskCacheStrategy.ALL).into(new GlideDrawableImageViewTarget(mImgView){
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                if (mImgView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    mImgView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ViewGroup.LayoutParams params = mImgView.getLayoutParams();
                int vw = mImgView.getWidth() - mImgView.getPaddingLeft() - mImgView.getPaddingRight();
                float scale = (float) vw / (float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                params.height = vh + mImgView.getPaddingTop() + mImgView.getPaddingBottom();
                mImgView.setLayoutParams(params);
            }
        });
    }
    /**
     * 横向充满纵向自适应(适用于ListView,RecycleView)
     * author LH
     * create at 2017/11/3 10:47
     */
    public static void loadLanFull(Context context, final String url, final ImageView mImgView){
        if (!url.equals(mImgView.getTag())) {
            Glide.with(context).load(url).error(R.drawable.ic_default).diskCacheStrategy(DiskCacheStrategy.ALL).into(new GlideDrawableImageViewTarget(mImgView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (mImgView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        mImgView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    ViewGroup.LayoutParams params = mImgView.getLayoutParams();
                    int vw = mImgView.getWidth() - mImgView.getPaddingLeft() - mImgView.getPaddingRight();
                    float scale = (float) vw / (float) resource.getIntrinsicWidth();
                    int vh = Math.round(resource.getIntrinsicHeight() * scale);
                    params.height = vh + mImgView.getPaddingTop() + mImgView.getPaddingBottom();
                    mImgView.setLayoutParams(params);
                    mImgView.setTag(url);

                }
            });
        }
    }

    public static void loadImageWithUri(Context context, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

}
