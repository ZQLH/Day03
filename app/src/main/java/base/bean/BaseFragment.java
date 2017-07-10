package base.bean;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/10.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }

    private void init() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadingPager loadingPager=new LoadingPager(UIUtils.getContext()) {
            /**
             * @des 在子线程中真正的加载具体的数据
             * @called triggerLoadData()方法被调用的时候
             */
            @Override
            public LoadedResult initData() {
                return BaseFragment.this.initData();
            }
            /**
             * @return
             * @des 决定成功视图长什么样子(需要定义成功视图)
             * @des 数据和视图的绑定过程
             * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
             */
            @Override
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }
        };
        loadingPager.triggerLoadData();
        return loadingPager;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    private void initListener() {

    }
    /**
     * @des 在子线程中真正的加载具体的数据
     * @des 在BaseFragent不知道如何具体的加载数据, 只能交给子类, 子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     * @called triggerLoadData()方法被调用的时候
     */
    public abstract LoadingPager.LoadedResult initData();

    /**
     * @return
     * @des 决定成功视图长什么样子(需要定义成功视图)
     * @des 数据和视图的绑定过程
     * @des 在BaseFragment中不知道成功视图具体是啥, 不知道具体数据是啥, 不知道数据和视图如何绑定, 交给子类, 子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法交给子类具体实现
     * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
     */
    protected abstract View initSuccessView();
}
