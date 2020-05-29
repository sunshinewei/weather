package com.app.weilong.lib.base.basemvp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.app.weilong.lib.R;
import com.app.weilong.lib.base.listener.TitleListener;
import com.app.weilong.lib.base.listener.ViewOnClickListener;
import com.app.weilong.lib.base.loading.view.LoadingDialog;
import com.app.weilong.lib.base.utils.BarUtils;
import com.app.weilong.lib.base.utils.LogUtils;
import com.app.weilong.lib.base.utils.SizeUtils;
import com.app.weilong.lib.base.view.CircleImageView;
import com.app.weilong.lib.base.view.ClassHeader;
import com.app.weilong.lib.base.view.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


public abstract class BaseActivity extends AppCompatActivity {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    //图片选择
    public static final int REQUEST_CODE_CHOOSE=200;
    public static final int REQUEST_CODE_CHOSE=201;

    protected Context mContext;
    public SmartRefreshLayout refresh;
    protected int page=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStatebar();

        BarUtils.setStatusBarLightMode(this.getWindow(),true);
        BarUtils.setStatusBarColor(this.getWindow(),getResources().getColor(R.color.color_ffffff));
//
        setContentView(getLayoutId());
        mContext=this;
        initTitleBar();
        initView();
//        refresh = findViewById(R.id.refresh);
        if (refresh!=null){
            initRefersh();
        }

        initBaseData(savedInstanceState);

    }

    private void initRefersh(){

        refresh.setRefreshHeader(new ClassHeader(this));

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

    protected void goActivity(Class clazz){
        Intent intent = new Intent(mContext,clazz);
        startActivity(intent);
    }


    protected TextView tvTitleCon;
    protected ImageView imgTitleBack;
    protected TextView tvTileRight;
    protected ImageView imgTitleRight;
    protected RelativeLayout rlRootBar;
    protected CircleImageView imgTitleHead;



    protected void initTitleBar(){

//        rlRootBar=findViewById(R.id.bar_root);

        if (rlRootBar!=null){

            ViewGroup.LayoutParams layoutPar = rlRootBar.getLayoutParams();

            layoutPar.height= SizeUtils.dip2px(mContext,BarUtils.getStatusBarHeight()+12);

            LogUtils.error("----"+SizeUtils.dip2px(mContext,BarUtils.getStatusBarHeight())+"-----"+SizeUtils.dp2Pix(mContext,BarUtils.getStatusBarHeight()));
            rlRootBar.setPadding(0, BarUtils.getStatusBarHeight(),0,0);
//            if (layoutPar instanceof RelativeLayout.LayoutParams){
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlRootBar.getLayoutParams();
////                layoutParams.setMargins(0,BarUtils.getStatusBarHeight(),0,0);
//                layoutParams.height=BarUtils.getStatusBarHeight()+SizeUtils.dp2Pix(mContext,43);
//                rlRootBar.setLayoutParams(layoutParams);
//            }else if (layoutPar instanceof LinearLayout.LayoutParams) {
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlRootBar.getLayoutParams();
////                layoutParams.setMargins(0, BarUtils.getStatusBarHeight(), 0, 0);
//                layoutParams.height=BarUtils.getStatusBarHeight()+SizeUtils.dp2Pix(mContext,43);
//                rlRootBar.setLayoutParams(layoutParams);
//
//            }
//            tvTitleCon=findViewById(R.id.tv_bar_title);
//            tvTileRight=findViewById(R.id.tv_bar_right);
//            imgTitleBack=findViewById(R.id.img_bar_back);
//            imgTitleRight=findViewById(R.id.img_bar_right);
//            imgTitleHead = findViewById(R.id.img_bar_head);
        }
    }

    protected void setBarRelate(View root){
        if (root!=null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) root.getLayoutParams();
            layoutParams.setMargins(0,BarUtils.getStatusBarHeight(),0,0);
            root.setLayoutParams(layoutParams);
        }
    }

    /**
     *
     */
    private void setStatebar(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){//5.0以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
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

    /**
     * 数据传输
     * @param label
     * @return
     */
    public Object getIntentData(String label){
        Intent intent = getIntent();
        Object Extra = intent.getSerializableExtra(label);
        return Extra;
    }




    public abstract int getLayoutId();

    protected abstract void initView();


    public abstract void initBaseData(@Nullable Bundle savedInstanceState);



    public void showBaseToast(String msg){
        ToastUtil.showToast(this,msg).show();
    }







    protected LoadingDialog mLoadingDialog;
    public void loadingDialog(String ...msg){
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog
                .setShowTime(0)
                .setLoadingText("加载中...")
                .show();
    }

    /**
     * 网络请求完成
     * 2.成功 成功中处理（无加载动画）
     */
    public void loadingSuccess() {
        if (mLoadingDialog != null) {
            mLoadingDialog.loadSuccess();
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
    public void LoadingFail(String ... msg) {
        if (mLoadingDialog != null) {
            mLoadingDialog.loadFailed();
        }
    }
    /**
     *
     *
     * 授权
     */
    protected void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showAlertDialog(getString(R.string.permission_title_rationale), rationale,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(BaseActivity.this,
                                    new String[]{permission}, requestCode);
                        }
                    }, getString(R.string.label_ok), null, getString(R.string.label_cancel));
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_STORAGE_WRITE_ACCESS_PERMISSION:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    saveCroppedImage();
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED) {
//        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                getString(R.string.permission_write_storage_rationale),
//                REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
//    }

    /**
     * This method shows dialog with given title & message.
     * Also there is an option to pass onClickListener for positive & negative button.
     *
     * @param title                         - dialog title
     * @param message                       - dialog message
     * @param onPositiveButtonClickListener - listener for positive button
     * @param positiveText                  - positive button text
     * @param onNegativeButtonClickListener - listener for negative button
     * @param negativeText                  - negative button text
     */
    private AlertDialog mAlertDialog;
    protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   @NonNull String positiveText,
                                   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   @NonNull String negativeText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        mAlertDialog = builder.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }





    /**
     * 加载错误页
     *
     * 在需要的地方调用
     */
    protected View viewStub;
    protected ImageView img_state;
    protected TextView tv_state;
    protected void setStatePager(int ... state){
//        if (mLoadingDialog!=null){
//            mLoadingDialog.close();
//        }
//        if (viewStub==null) {
//            viewStub =((ViewStub)findViewById(R.id.state));
//            viewStub.setVisibility(View.VISIBLE);
//        }else {
//            viewStub.setVisibility(View.VISIBLE);
//        }
//        if (img_state==null){
//            img_state= findViewById(R.id.img_state);
//            tv_state = findViewById(R.id.tv_state);
//        }
//        if (state!=null){
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



}
