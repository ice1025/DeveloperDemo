package com.bing.developer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.*;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ice on 2016/5/31.
 */
public class WelcomeActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{

    private ViewPager vp;
    private LinearLayout directorLayout;//图片位置指示器
    private Button button;//点击跳转按钮


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.fellow_main);

        //实例化控件
        directorLayout = (LinearLayout) findViewById(R.id.director);
        button = (Button) findViewById(R.id.btn);
        vp = (ViewPager) findViewById(R.id.welcome_pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 当viewPager滑动时，页面被选中时调用
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        int count = directorLayout.getChildCount();
        /**
         * 指示器的对象顺序和页面显示的顺序一样的设置为yellow其他为white
         */
        for (int i = 0; i < count; i++) {
            ImageView iv = (ImageView) directorLayout.getChildAt(i);
            if(i==position){
                iv.setBackgroundResource(R.mipmap.yellow);
            }else{
                iv.setBackgroundResource(R.mipmap.white);
            }
        }
        //最后一页时候，显示按钮
        if(position==count-1){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
