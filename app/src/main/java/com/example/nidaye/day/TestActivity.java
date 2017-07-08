package com.example.nidaye.day;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nidaye on 2017/7/5.
 */

public class TestActivity extends AppCompatActivity {
    private ScaleBehavior fab;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    public static  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("条目" + i);
        }
        recyclerView.setAdapter(new MyAdapter(datas));
        bottomSheetBehavior=BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
        bottomSheetBehavior.setPeekHeight(0);
        fab = ScaleBehavior.from(findViewById(R.id.fab));


        fab.setnoName(new ScaleBehavior.noName() {
            @Override
            public void whatMethod(boolean isShow) {
                bottomSheetBehavior.setState(isShow?bottomSheetBehavior.STATE_EXPANDED:bottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }
}
