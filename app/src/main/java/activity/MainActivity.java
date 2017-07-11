package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.astuetz.PagerSlidingTabStripExtends;
import com.example.nidaye.day.R;

import adapter.MainFragmentPagerAdapter;
import base.bean.BaseFragment;
import base.bean.FragmentFactory;
import base.bean.LoadingPager;
import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/8.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "huan";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ViewPager mMainViewpager;
    private PagerSlidingTabStripExtends mMainTabs;
    private String[] mMainTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mMainViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        mMainTabs = (PagerSlidingTabStripExtends) findViewById(R.id.main_tabs);
        initActionBar();
        initActionBarDrawerToggle();
        initData();
        initListener();
    }

    private void initListener() {
        final MyOnpageChangeListener myOnpageChangeListener=new MyOnpageChangeListener();
        mMainTabs.setOnPageChangeListener(myOnpageChangeListener);
        mMainViewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //viewpager已经展示给用户看
                myOnpageChangeListener.onPageSelected(0);
                mMainViewpager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }
    class MyOnpageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG, "onPageSelected: 选中:"+mMainTitles[position]);
            // 根据position找到对应页面的Fragment
            BaseFragment baseFragment = FragmentFactory.mCacheFragments.get(position);
            // 拿到Fragment里面的LoadingPager
            LoadingPager loadingPager=baseFragment.getLoadingPager();
            // 触发加载数据
            loadingPager.triggerLoadData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void initData() {
        mMainTitles = UIUtils.getStrings(R.array.main_titles);
        MainFragmentPagerAdapter adapter=new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setMainTitles(mMainTitles);
        mMainViewpager.setAdapter(adapter);
        mMainTabs.setViewPager(mMainViewpager);
    }

    private void initActionBar(){
        ActionBar supportActionBar=getSupportActionBar();
        supportActionBar.setTitle("GooglePlay");
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayUseLogoEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void initActionBarDrawerToggle(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);
        //替换回退部分的UI效果
        mToggle.syncState();
        mDrawerLayout.setDrawerListener(mToggle);
        
    }

    //左上方的图标被点击后调用该方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //点击toggle可以控制drawerlayout的打开和关闭
                mToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
