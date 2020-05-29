package com.app.weilong.lib.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.weilong.lib.base.utils.GlideUtils;

import java.util.ArrayList;

/**
 * create by weilong on 2020/4/18
 * email: 1436699184@qq.com
 */
public abstract class BaseRcyAdapter<D> extends RecyclerView.Adapter<BaseRcyAdapter.BaseViewHolder> {

    protected ArrayList<D> mData;
    private int resId;


    public BaseRcyAdapter(ArrayList<D> mData,int resId){
       this.mData=mData==null?new ArrayList<D>():mData;
       if (resId!=0){
           this.resId=resId;
       }else {
           throw new NullPointerException("设置资源文件");
       }
    }

    public BaseRcyAdapter<D> setLoadmData(ArrayList<D> mData) {
        this.mData.addAll(mData);
        notifyDataSetChanged();
        return this;
    }

    public ArrayList<D> getmData() {
        if (mData==null){
           return new ArrayList<>();
        }
        return mData;
    }

    public BaseRcyAdapter<D> setmData(ArrayList<D> mData) {
        this.mData=mData==null?new ArrayList<D>():mData;
        notifyDataSetChanged();
        return this;
    }



    @NonNull
    @Override
    public BaseRcyAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mRoot;

        mRoot = LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);

        return  new BaseViewHolder(mRoot);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRcyAdapter.BaseViewHolder holder, int position) {
        bindView(holder,position);
    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }


    public abstract void bindView(@NonNull BaseRcyAdapter.BaseViewHolder holder, int position);

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
        }

        /**
         * 从ItemView获取View
         * @param id  ItemView里包含的ViewId
         * @param <V> 返回View
         * @return
         */
        public <V extends View> V getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            return (V) view;
        }

        /**
         * 设置TextView的值
         * @param viewId
         * @param text
         * @return
         */
        public BaseViewHolder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }
        /**
         * 设置TextView的值
         * @param viewId
         * @param color
         * @return
         */
        public BaseViewHolder setTextColor(int viewId, int color) {
            TextView tv = getView(viewId);
            tv.setTextColor(color);
            return this;
        }



        /**
         * 设置ImageView的值
         * @param viewId
         * @param resId
         * @return
         */
        public BaseViewHolder setImageResource(int viewId, int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        /**
         * 设置ImageView的值
         * 第三方  ImageLoder Glide Picasso
         * 不能直接写死第三方图片加载
         * 使用自己的一套规范  ImageLoder
         * @param viewId
         * @return
         */
        public BaseViewHolder setImagePath(Context mctx,int viewId, String imageLoder) {
            ImageView view = getView(viewId);
            GlideUtils.loadGlide(mctx,imageLoder,view);
            return this;
        }

        //图片加载 （对第三方库加载图片等封装）
        public abstract class ImageLoder{
            private String path;
            public ImageLoder(String path){
                this.path = path;
            }
            //需要复写该方法加载图片
            public abstract void loadImage(ImageView imageView,String path);
            public String getPath() {
                return path;
            }
        }

        /**
         * 设置是否可见
         * @param viewId
         * @param visible
         * @return
         */
        public BaseViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        /**
         * 设置tag
         * @param viewId
         * @param tag
         * @return
         */
        public BaseViewHolder setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return this;
        }

        public BaseViewHolder setTag(int viewId, int key, Object tag) {
            View view = getView(viewId);
            view.setTag(key, tag);
            return this;
        }

        /**
         * 设置Checkable
         * @param viewId
         * @param checked
         * @return
         */
        public BaseViewHolder setChecked(int viewId, boolean checked) {
            Checkable view = (Checkable) getView(viewId);
            view.setChecked(checked);
            return this;
        }

        //点击事件
        public BaseViewHolder setOnClickListener(int viewId,View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        //触摸事件
        public BaseViewHolder setOnTouchListener(int viewId,View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        //长按事件
        public BaseViewHolder setOnLongClickListener(int viewId,View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

    }
}
