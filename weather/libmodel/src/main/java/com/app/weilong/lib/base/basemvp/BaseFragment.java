package com.app.weilong.lib.base.basemvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.weilong.lib.R;
import com.app.weilong.lib.base.listener.TitleListener;
import com.app.weilong.lib.base.listener.ViewOnClickListener;
import com.app.weilong.lib.base.loading.view.LoadingDialog;
import com.app.weilong.lib.base.utils.BarUtils;
import com.app.weilong.lib.base.utils.SizeUtils;
import com.app.weilong.lib.base.view.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public abstract class BaseFragment extends Fragment {


    Bundle mbundleData;
    protected Context mContext;
    View viewRoot;

    protected int page=1;
    public SmartRefreshLayout refresh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewRoot = inflater.inflate(getLayoutId(), container, false);

        mContext=getContext();

//        BarUtils.setStatusBarLightMode(getActivity().getWindow(),true);
//        BarUtils.setStatusBarColor(getActivity(),getResources().getColor(R.color.color_ffffff));

        initTitleBar(viewRoot);
//        refresh = viewRoot.findViewById(R.id.refresh);
        if (refresh!=null){
            initRefersh();
        }
        initViews(viewRoot);
        initData(savedInstanceState);
        isFirstLoad=true;
        if (getUserVisibleHint()){
            lazyLoad();
            isFirstLoad = false;//视图创建完成，将变量置为true
        }
        return viewRoot;
    }

    protected TextView tvTitleCon;
    protected ImageView imgTitleBack;
    protected TextView tvTileRight;
    protected ImageView imgTitleRight;
    protected RelativeLayout rlRootBar;
    protected void initTitleBar(View view){
        rlRootBar=view.findViewById(R.id.bar_root);
        if (rlRootBar!=null){
            ViewGroup.LayoutParams layoutPar = rlRootBar.getLayoutParams();
            layoutPar.height= SizeUtils.dip2px(mContext, BarUtils.getStatusBarHeight()+14);
            rlRootBar.setPadding(0, BarUtils.getStatusBarHeight(),0,0);
            tvTitleCon=view.findViewById(R.id.tv_bar_title);
            tvTileRight=view.findViewById(R.id.tv_bar_right);
            imgTitleBack=view.findViewById(R.id.img_bar_back);
            imgTitleRight=view.findViewById(R.id.img_bar_right);
        }
    }

    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible=false;

    //是否已经创建视图
    private boolean isViewCreate=false;

    //第一次加载数据
    private boolean isFirstLoad = false;

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        isPrepared = true;
//        isViewCreate=true;
//        lazyLoad();
//    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            load();
        }
        //懒加载
        if (isVisibleToUser && isFirstLoad){
            lazyLoad();
            isFirstLoad=false;
        }
    }


    protected void load(){
        System.out.println("--------------------------load");
    }
    protected void lazyLoad() {
        lazyLoadData();
    }

    public abstract void lazyLoadData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad=false;
    }

    /**
     * 标题内容
     * @param title
     */
    protected void setBarTile(String title){
        tvTitleCon.setVisibility(View.VISIBLE);
        tvTitleCon.setText(title);
    }

    /**
     * 标题右
     * @param tileRight
     */
    protected void setBarRightTile(String tileRight, final TitleListener mListener){
        tvTileRight.setVisibility(View.VISIBLE);
        tvTileRight.setText(tileRight);
        tvTileRight.setOnClickListener(new ViewOnClickListener() {
            @Override
            public void onSingleClick(View v) {
                mListener.onClick(v);
            }
        });
    }

    /**
     *
     * @param mListener
     */
    protected void setBarLeftImage(final TitleListener mListener){
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new ViewOnClickListener() {
            @Override
            public void onSingleClick(View v) {
                mListener.onClick(v);
            }
        });
    }


    /**
     *
     * @param mListener
     */
    protected void setBarRightImage(final TitleListener mListener){
        imgTitleRight.setVisibility(View.VISIBLE);
        imgTitleRight.setOnClickListener(new ViewOnClickListener() {
            @Override
            public void onSingleClick(View v) {
                mListener.onClick(v);
            }
        });

    }






    protected  void onBaseRefresh(RefreshLayout refreshLayout){

    }

    protected  void onBaseLoadMore(RefreshLayout refreshLayout){

    }

    /**
     * 刷新也加载更多 结束状态
     */
    protected void setEndLoad(){
        if (refresh!=null){
            refresh.finishLoadMore();
            refresh.finishRefresh();
        }
        if (mLoadingDialog!=null){
            mLoadingDialog.close();
        }
    }


    protected void setBarRelate(View root){
        if (root!=null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) root.getLayoutParams();
            layoutParams.setMargins(0,BarUtils.getStatusBarHeight(),0,0);
            root.setLayoutParams(layoutParams);
        }
    }


    public abstract int getLayoutId();

    public abstract void initViews(View viewRoot);

    public abstract void initData(@Nullable Bundle savedInstanceState);


    public Bundle getBundle(){
        mbundleData = getArguments();
        if (mbundleData!=null){
            return mbundleData;
        }
        return null;
    }


    LoadingDialog mLoadingDialog;
    public void loadingDialog(){
        mLoadingDialog = new LoadingDialog(getContext());
        mLoadingDialog
                    .setShowTime(1)
                    .setLoadingText("加载中...")
                    .show();

    }

    /**
     * 网络请求完成
     * 2.成功 成功中处理（无加载动画）
     */
    public void loadingSuccess() {
        if (mLoadingDialog != null) {
            mLoadingDialog.closeSuccessAnim();
            mLoadingDialog.loadSuccess();
        }
        if (refresh!=null){
            setEndLoad();
        }
    }


    /**
     * 网络请求完成
     * 2.成功 有成功加载动画
     */
    public void loadingOnSuccess() {
        if (mLoadingDialog != null) {
            mLoadingDialog.close();
        }
        if (refresh!=null){
            setEndLoad();
        }
    }


    /**
     * 1.失败 失败中加载dialog
     * @param msg
     */
    public void loadingFail(String msg) {
        if (mLoadingDialog != null) {
            mLoadingDialog.loadFailed();
        }
        if (refresh!=null){
            setEndLoad();
        }
    }

    private void initRefersh(){
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (viewStub!=null){
                    viewStub.setVisibility(View.GONE);
                }
                onBaseRefresh(refreshLayout);
            }
        });

        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onBaseLoadMore(refreshLayout);
            }
        });
    }


    protected View viewStub;
    protected ImageView img_state;
    protected TextView tv_state;
    protected void setStatePager(int ... state){
//        if (viewStub==null) {
//            System.out.println("-----------------viewStubnull");
//            viewStub =((ViewStub)viewRoot.findViewById(R.id.state));
//            viewStub.setVisibility(View.VISIBLE);
//        }else {
//            System.out.println("-----------------!null");
//            viewStub.setVisibility(View.VISIBLE);
//        }
//        if (img_state==null){
//            img_state= viewRoot.findViewById(R.id.img_state);
//            tv_state = viewRoot.findViewById(R.id.tv_state);
//        }
//
//        if (state!=null && img_state!=null){
//            if (state.length==0){//站无数据
//                img_state.setImageDrawable(getResources().getDrawable(R.mipmap.undraw_not_found_60pq));
//                tv_state.setText("很抱歉，暂无相关数据！");
//                if (refresh!=null){
//                    refresh.setEnableLoadMore(false);
//                }
//            }else if (state[0]==1){//网络错误
//                img_state.setImageDrawable(getResources().getDrawable(R.mipmap.undraw_taken_if77));
//                tv_state.setText("请您检查相关网络！");
//                if (refresh!=null){
//                    refresh.setEnableLoadMore(false);
//                }
//            }else if (state[0]==0){
//                img_state.setImageDrawable(getResources().getDrawable(R.mipmap.undraw_not_found_60pq));
//                tv_state.setText("很抱歉，暂无相关数据！");
//                if (refresh!=null){
//                    refresh.setEnableLoadMore(false);
//                }
//            }
//        }

    }

    public void showBaseToast(String msg){
        ToastUtil.showToast(mContext,msg).show();
    }

}
