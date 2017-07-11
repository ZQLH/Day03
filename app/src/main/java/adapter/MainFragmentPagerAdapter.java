package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.nidaye.day.R;

import base.bean.FragmentFactory;
import utils.LogUtils;
import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/9.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter{


    private  String[] mMainTitles;
    public void setMainTitles(String[] mMainTitles){
        this.mMainTitles=mMainTitles;
    }

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mMainTitles = UIUtils.getStrings(R.array.main_titles);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= FragmentFactory.createFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        if (mMainTitles!=null){
            return mMainTitles.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMainTitles[position];
    }

}
