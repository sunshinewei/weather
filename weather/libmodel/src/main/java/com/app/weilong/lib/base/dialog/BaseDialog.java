package com.app.weilong.lib.base.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;


/**
 * create by weilong on 2020/4/13
 * email: 1436699184@qq.com
 */
public abstract class BaseDialog extends DialogFragment {


    @Override
    public void onStart() {
        super.onStart();
//        Window window = getDialog().getWindow();
////        window.setBackgroundDrawableResource(R.color.alibc_transparent);
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        attributes.gravity= Gravity.CENTER;
//        attributes.height= ScreenUtils.getScreenHeight()/5;
//        //设置背景颜色,只有设置了这个属性,宽度才能全屏MATCH_PARENT
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        attributes.width= ScreenUtils.getAppScreenWidth()/2;
//        window.setAttributes(attributes);

        setConfig();
    }






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    /**
//     *
//     * @return
//     */
    public abstract int getLayoutId();


    DialogCallBack mCallBak;
    /**
     *
     * @param viewRoot
     */
    public void initView(View viewRoot){
        if (mCallBak!=null){
            mCallBak.initView(viewRoot);
        }
    }

    public BaseDialog setmCallBak(DialogCallBack mCallBak) {
        this.mCallBak = mCallBak;
        return this;
    }

    /**
     *
     */
    public void initData(){
        if (mCallBak!=null){
            mCallBak.initDialogData();
        }
    }

    public abstract void setConfig();



    public BaseDialog show(FragmentActivity mAct){
        show(mAct.getSupportFragmentManager(),"");
        return this;
    }


}
