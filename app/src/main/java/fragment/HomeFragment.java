package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import base.bean.BaseFragment;
import base.bean.LoadingPager;
import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/9.
 */

public class HomeFragment extends BaseFragment {

    @Override
    public LoadingPager.LoadedResult initData() {
        SystemClock.sleep(2000);//模拟耗时的网络请求
        return LoadingPager.LoadedResult.SUCCESS;
    }

    @Override
    protected View initSuccessView() {
        TextView successView = new TextView(getActivity());
        successView.setGravity(Gravity.CENTER);
        successView.setText(this.getClass().getSimpleName());
        successView.setTextColor(Color.RED);
        return successView;
    }

}

