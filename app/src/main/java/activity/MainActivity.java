package activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.nidaye.day.R;

import adapter.MainFragmentPagerAdapter;
import utils.PagerSlidingTabStripExtends;
import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/8.
 */

public class MainActivity extends AppCompatActivity {

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
        initView();
        initActionBarDrawerToggle();
        initData();
    }

    private void initData() {
        mMainTitles = UIUtils.getStrings(R.array.main_titles);
        MainFragmentPagerAdapter adapter=new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setMainTitles(mMainTitles);
        mMainViewpager.setAdapter(adapter);
        mMainTabs.setViewPager(mMainViewpager);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
    }

    private void initActionBar(){
        ActionBar supportActionBar=getSupportActionBar();
        supportActionBar.setTitle("GooglePlay");
//        supportActionBar.setSubtitle("副标题");
//        supportActionBar.setIcon(R.drawable.ic_launcher);
//        supportActionBar.setLogo(R.mipmap.ic_launcher);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayUseLogoEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void initActionBarDrawerToggle(){
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
    public void notifyData(){

    }
}
