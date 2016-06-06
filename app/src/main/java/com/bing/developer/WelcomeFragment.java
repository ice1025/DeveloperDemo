package com.bing.developer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by ice on 2016/5/31.
 */
public class WelcomeFragment extends Fragment{
    View view = null;
    int imgId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.welcome_fragment,null);

        ImageView fragmentView = (ImageView) view.findViewById(R.id.welcome_Img);
        fragmentView.setBackgroundResource(imgId);

        return view;
    }
    public void setImg(int imgId){
        this.imgId=imgId;
    }
}
