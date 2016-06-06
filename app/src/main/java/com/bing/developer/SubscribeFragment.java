package com.bing.developer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ice on 2016/5/31.
 */
public class SubscribeFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content,null);
        TextView tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        tvContent.setText("订阅");
        return rootView;
    }
}
