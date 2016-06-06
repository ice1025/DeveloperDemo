package com.bing.developer;

import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    private GiftEXchangeFragment giftEXchangeFragment;
    private DrawerLayout drawerLayout;

    private RelativeLayout rlHome,rlGift;

    private int currentId = R.id.rl_home;//默认首页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame,mainFragment).commit();
        findViewById(R.id.iv_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv_menu:
                        drawerLayout.openDrawer(Gravity.LEFT);
                        break;
                }
            }
        });
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);

        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlGift = (RelativeLayout) findViewById(R.id.rl_gift);

        rlHome.setOnClickListener(menuItemClick);
        rlGift.setOnClickListener(menuItemClick);

        rlHome.setSelected(true);

        setWindowStatus();




    }

    /**
     * 设置状态栏
     */
    private void setWindowStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 设置状态栏颜色
            getWindow().setBackgroundDrawableResource(R.color.main_color);
        }
    }


    /**
     * 显示之前隐藏所有fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction){
        if (mainFragment != null)//不为空才隐藏,如果不判断第一次会有空指针异常
            transaction.hide(mainFragment);
        if (giftEXchangeFragment != null)
            transaction.hide(giftEXchangeFragment);
    }

    private View.OnClickListener menuItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(currentId!=v.getId()){//防止重复点击
                currentId=v.getId();
                noItemSelect();//没有选中
                changeFragment(v.getId());//改变Fragment的显示

                switch (v.getId()){
                    case R.id.rl_home:
                        rlHome.setSelected(true);
                        break;
                    case R.id.rl_gift:
                        rlGift.setSelected(true);
                        break;
                }
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    };

    private void changeFragment(int resId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);//隐藏所有fragment
        if(resId==R.id.rl_home){//主页
            if(mainFragment==null){
                mainFragment = new MainFragment();
                transaction.add(R.id.content_frame,mainFragment);
            }else {
                transaction.show(mainFragment);
            }
        }else if(resId==R.id.rl_gift){
            if(giftEXchangeFragment==null){
                giftEXchangeFragment=new GiftEXchangeFragment();
                transaction.add(R.id.content_frame,giftEXchangeFragment);
            }else{
                transaction.show(giftEXchangeFragment);
            }
        }
        transaction.commit();//一定记得提交事务
    }

    private void noItemSelect() {
        rlHome.setSelected(false);
        rlGift.setSelected(false);
    }
}
