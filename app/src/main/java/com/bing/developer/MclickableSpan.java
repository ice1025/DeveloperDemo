package com.bing.developer;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by ice on 2016/5/31.
 */
public class MclickableSpan extends ClickableSpan{
    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
//        ds.setColor(ds.linkColor);
//        ds.setUnderlineText(true);
    }
}
