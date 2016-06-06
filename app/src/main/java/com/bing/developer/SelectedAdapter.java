package com.bing.developer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2016/6/1.
 */
public class SelectedAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<SelectedArticle> selectedArticles;

    public SelectedAdapter(Context context){
        inflater = LayoutInflater.from(context);
        selectedArticles = new ArrayList<SelectedArticle>();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            SelectedArticle selectedArticle  = new SelectedArticle(i,"标题",i,i,"");
            selectedArticles.add(selectedArticle);
        }
    }


    @Override
    public int getCount() {
        return selectedArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return selectedArticles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.fragment_selected_item,null);
            holder = new viewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.like = (TextView) convertView.findViewById(R.id.tv_like);
            holder.comment = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(holder);
        }else{
            holder = (viewHolder) convertView.getTag();
        }
        SelectedArticle s = selectedArticles.get(position);
        holder.title.setText(s.getTitle());
        holder.like.setText(""+s.getLikeNUmber());
        holder.comment.setText(""+s.getCommentNumber());
        return convertView;
    }
    private class viewHolder{
        private TextView title;
        private TextView like;
        private TextView comment;
    }
}
