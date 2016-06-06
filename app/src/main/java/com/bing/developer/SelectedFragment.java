package com.bing.developer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ice on 2016/5/31.
 */
public class SelectedFragment extends Fragment{
    private ViewPager viewPager;
    private SelectedPagerAdapter selectedPagerAdapter;

    private ImageView[] tips;//底部

    private Timer timer;
    private final int CAROUSEL_TIME = 3000;//滚动间隔

    private int currentIndex=0;//当前选中

    private TextView tvContent;
    private String[] caroussPageStr = new String[]{"呵呵","哈哈","嘻嘻"};

    private ListView listView;
    private SelectedAdapter selectedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_selected,null);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_selected_header,null);

        tvContent = (TextView) headView.findViewById(R.id.tv_content);
        tvContent.setText(caroussPageStr[0]);

        viewPager = (ViewPager) headView.findViewById(R.id.viewpager);
        selectedPagerAdapter= new SelectedPagerAdapter(getActivity(),carousePagerSelectView);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setAdapter(selectedPagerAdapter);

        ViewGroup group = (ViewGroup) headView.findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            if(i==0){
                imageView.setBackgroundResource(R.mipmap.page_indicator_focused);
            }else{
                imageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }

            tips[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;// 设置点点点view的左边距
            layoutParams.rightMargin = 10;// 设置点点点view的右边距
            group.addView(imageView, layoutParams);
        }

        timer = new Timer(true);//初始化计时器
        timer.schedule(task,0,CAROUSEL_TIME);

        listView=(ListView) rootView.findViewById(R.id.list);
        listView.addHeaderView(headView);
        listView.setAdapter(selectedAdapter=new SelectedAdapter(getActivity()));
        return rootView;
    }

    @Override
    public void onDestroy() {
        task.cancel();
        System.exit(0);
    }

    private ICarousePagerSelectView carousePagerSelectView = new ICarousePagerSelectView() {
        @Override
        public void carouseSelect(int index) {
            Toast.makeText(getActivity(), caroussPageStr[index], Toast.LENGTH_SHORT).show();
        }
    };

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tvContent.setText(caroussPageStr[position]);
            setImageBackground(position);
            currentIndex=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    TimerTask task = new TimerTask() {
        public void run() {
            handler.sendEmptyMessage(CAROUSEL_TIME);
        }
    };
    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CAROUSEL_TIME:
                    if(currentIndex>=tips.length-1){//已经滚动到最后,从第一页开始
                        viewPager.setCurrentItem(0);
                    }else{//开始下一页
                        viewPager.setCurrentItem(currentIndex+1);
                    }
                    break;
            }
        };
    };
    public void setImageBackground(int selectItem) {
        for (int i = 0; i < tips.length; i++) {
            if(i==selectItem){
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }
}
