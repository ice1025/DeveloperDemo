package com.bing.developer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2016/5/31.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    List<Integer> imgs = null;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        imgs= new ArrayList<Integer>();
        imgs.add(R.mipmap.tutorial_1);
        imgs.add(R.mipmap.tutorial_2);
        imgs.add(R.mipmap.tutorial_3);
        imgs.add(R.mipmap.tutorial_4);
    }

    @Override
    public Fragment getItem(int position) {
        //得到要显示的对象并初始化
        WelcomeFragment fm = new WelcomeFragment();
        fm.setImg(imgs.get(position));
        return fm;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}
