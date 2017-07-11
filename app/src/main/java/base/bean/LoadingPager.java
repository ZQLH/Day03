package base.bean;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.nidaye.day.R;

import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/10.
 */

public abstract class LoadingPager extends FrameLayout {
    /*
    * 1.添加4个常量用来表示页面的四种状态；
      2.并添加一个成员变量来表示当前页面的状态；
      3.添加一个方法来根据当前状态刷新页面视图显示，并在View一创建的时候就调用一下这个方法来刷新UI
    * */
    public static final int STATE_LOADING = 0;//加载中
    public static final int STATE_ERROR = 1;//错误
    public static final int STATE_SUCCESS = 2;//成功
    public static final int STATE_EMPTY = 3;//空
    public int mCurState = STATE_LOADING;//默认是加载中的情况


    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;
    private LoadDataTask mLoad;

    public LoadingPager(Context context) {
        super(context);
        initCommonView();
    }

    private void initCommonView() {
        //加载中视图
        mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(mLoadingView);

        //错误视图
        mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        Button bt= (Button) mErrorView.findViewById(R.id.error_btn_retry);
        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerLoadData();
            }
        });
        this.addView(mErrorView);

        //空视图
        mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        this.addView(mEmptyView);

        //根据当前页面状态刷新UI
        refreshViewByState();
    }

    //根据mCurState来确定显示哪一个View,以及View的样子
    private void refreshViewByState() {


        if (mCurState == STATE_LOADING) {
            mLoadingView.setVisibility(View.VISIBLE);
        } else {
            mLoadingView.setVisibility(View.GONE);
        }

        //控制 错误视图 显示隐藏
        if (mCurState == STATE_ERROR) {
            mErrorView.setVisibility(View.VISIBLE);
        } else {
            mErrorView.setVisibility(View.GONE);
        }

        //控制 空视图 显示隐藏
        if (mCurState == STATE_EMPTY) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mSuccessView == null && mCurState == STATE_SUCCESS) {
            mSuccessView = initSuccessView();
            this.addView(mSuccessView);
        }
        // 控制 成功视图的 显示隐藏
        if (mSuccessView != null) {
            if (mCurState == STATE_SUCCESS) {
                mSuccessView.setVisibility(View.VISIBLE);
            } else {
                mSuccessView.setVisibility(View.GONE);
            }
    }
    }
    /**
     * @des 触发加载数据
     * @called 外界想让LoadingPager触发加载数据的时候调用
     */
    public void triggerLoadData() {
        if (mCurState==STATE_SUCCESS){
            return;
        }
        if (mLoad==null){
            //控制数据加载之前显示加载中的师徒
            mCurState=STATE_LOADING;
            refreshViewByState();
            mLoad = new LoadDataTask();
            new Thread(mLoad).start();
        }
    }

    //改方法被触发加载数据的时候调用，且可以实现更新主线程中的UI的方法
    class LoadDataTask implements Runnable {
        @Override
        public void run() {
            Log.i("testtime",android.os.Process.myTid()+"1");
            //真正的在子线程中加载具体的数据-->得到数据
            LoadedResult tempState = initData();
            //处理数据
            mCurState = tempState.getState();
            MyApplication.getMainThreadHandler().post(new Runnable() {
                @Override
                public void run() {
                    // 刷新UI(决定到底提供4种视图中的哪一种视图)
                    refreshViewByState();//mCurState-->Int

                }
            });
            mLoad=null;
        }

    }

    /**
     * @des 在子线程中真正的加载具体的数据
     * @des 在LoadingPager中, 不知道如何具体加载数据, 交给子类,子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     * @called triggerLoadData()方法被调用的时候
     */
    public abstract LoadedResult initData();

    /**
     * @return
     * @des 决定成功视图长什么样子(需要定义成功视图)
     * @des 数据和视图的绑定过程
     * @des 在LoadingPager中, 其实不知道成功视图具体长什么样子, 更加不知道视图和数据如何绑定, 交给子类, 必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
     */
    public abstract View initSuccessView();
    /**
     *  标识数据加载结果的枚举类
     */
    public enum LoadedResult{
        /**
         * STATE_ERROR = 1;//错误
         * STATE_SUCCESS = 2;//成功
         * STATE_EMPTY = 3;//空
         */
        SUCCESS(STATE_SUCCESS),ERROR(STATE_ERROR),EMPTY(STATE_EMPTY);
        private  int state;
        LoadedResult(int state) {
            this.state=state;
        }

        public int getState() {
            return state;
        }
    }

}
