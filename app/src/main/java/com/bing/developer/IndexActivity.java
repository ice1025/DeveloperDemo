package com.bing.developer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ice on 2016/5/31.
 */
public class IndexActivity extends Activity implements View.OnClickListener{
//    private TextView textView;
    private Button gotoMain;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = LayoutInflater.from(this).inflate(R.layout.index,null);
        setContentView(rootView);



        handler=new Handler();

//        setContentView(R.layout.index);

        AlphaAnimation aa = new AlphaAnimation(0.1f,1.0f);
        aa.setDuration(3000);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(isFirst()){
                            Intent intent = new Intent(IndexActivity.this,WelcomeActivity.class);
                            IndexActivity.this.startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(IndexActivity.this,MainActivity.class);
                            IndexActivity.this.startActivity(intent);
                            finish();
                        }

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rootView.startAnimation(aa);
//        textView= (TextView) findViewById(R.id.gotomain);
        gotoMain= (Button) findViewById(R.id.gotomain_btn);
        gotoMain.setOnClickListener(this);
        View v=findViewById(R.id.gotomain);
        v.getBackground().setAlpha(100);//0-255之间
//        SpannableString spannableClickString = new SpannableString("点击跳过>>");
//        MclickableSpan clickableSpan = new MclickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(IndexActivity.this,"我要跳过啦啊哈哈",Toast.LENGTH_SHORT).show();
//            }
//        };
//        spannableClickString.setSpan(clickableSpan,0,spannableClickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        textView.setText(spannableClickString);
    }

    @Override
    public void onClick(View v) {

        if(isFirst()){
            Intent intent = new Intent(IndexActivity.this,WelcomeActivity.class);
            IndexActivity.this.startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(IndexActivity.this,MainActivity.class);
            IndexActivity.this.startActivity(intent);
            finish();
        }
    }

    /**
     * 是否是第一次运行
     * @return
     */
    private boolean isFirst(){
        /**
         * 获得一个SharedPreferences对象，第一个参数为对象文件的名字，
         * 第二个参数为对此对象的操作权限，MODE_PRIVATE权限是指只能够被本应用所读写。
         */
        SharedPreferences preferences = getSharedPreferences("phone",Context.MODE_PRIVATE);
        boolean user_first = preferences.getBoolean("FIRST",true);
        if(user_first){//为true则为第一次
            preferences.edit().putBoolean("FIRST",false).commit();
            return true;
        }else{
            return false;
        }
    }
}
