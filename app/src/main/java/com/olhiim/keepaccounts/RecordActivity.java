package com.olhiim.keepaccounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.olhiim.keepaccounts.adapter.RecordPagerAdapter;
import com.olhiim.keepaccounts.frag_record.IncomeFragment;
import com.olhiim.keepaccounts.frag_record.BaseRecordFragment;
import com.olhiim.keepaccounts.frag_record.OutcomeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //1.查找控件
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);
        //2.设置ViewPager加载页面
        initPager();
    }

    private void initPager() {
        //初始化viewPager页面的集合
        List<Fragment> fragmentList = new ArrayList<>();
        //创建收入和支出页面，放置在Fragment中
        OutcomeFragment outFrag = new OutcomeFragment(); // 支出
        IncomeFragment inFrag = new IncomeFragment();
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);

        //创建适配器
        RecordPagerAdapter pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager(), fragmentList);
        //设置适配器对象
        viewPager.setAdapter(pagerAdapter);
        //将Tablayout和ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
    }

    /*点击事件*/
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.record_iv_back:
                finish();
                break;
        }
    }
}