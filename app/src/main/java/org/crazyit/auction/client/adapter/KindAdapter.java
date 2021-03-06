package org.crazyit.auction.client.adapter;

import org.crazyit.auction.client.R;
import org.crazyit.auction.client.bean.KindBean;
import org.crazyit.auction.client.util.Tools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class KindAdapter extends BaseAdapter {
    // 需要包装的JSONArray
    private List<KindBean> kindArray;
    private Context ctx;
    private LayoutInflater inflater;

    public KindAdapter(List<KindBean> kindArray
            , Context ctx) {
        this.kindArray = kindArray;
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        // 返回ListView包含的列表项的数量
        return kindArray.size();
    }

    @Override
    public KindBean getItem(int position) {
        // 获取指定列表项所包装的JSONObject
        return kindArray.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {//如果该界面缓存为空
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_kind, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_kind);
            holder.kindName = (TextView) convertView.findViewById(R.id.tv_kind_name);
            holder.kindDesc= (TextView) convertView.findViewById(R.id.tv_kind_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position < getCount()) {
            //图片加载框架
            if (kindArray.get(position).getKindIcon()!=null){
                Glide.with(ctx).load(kindArray.get(position).getKindIcon())
                        .centerCrop().placeholder(R.drawable.ic_launcher)
                        .into(holder.imageView);
            }
            holder.kindName.setText(kindArray.get(position).getKindName());
            holder.kindDesc.setText(kindArray.get(position).getKindDesc());

        }
        return convertView;
//        // 定义一个线性布局管理器
//        LinearLayout container = new LinearLayout(ctx);
//        // 设置为水平的线性布局管理器
//        container.setOrientation(LinearLayout.VERTICAL);
//        // 定义一个线性布局管理器
//        LinearLayout linear = new LinearLayout(ctx);
//        // 设置为水平的线性布局管理器
//        linear.setOrientation(LinearLayout.HORIZONTAL);
//        // 创建一个ImageView
//        ImageView iv = new ImageView(ctx);
//        iv.setPadding(10, 0, 20, 0);
//        iv.setImageResource(R.drawable.item);
//        // 将图片添加到LinearLayout中
//        linear.addView(iv);
//        // 创建一个TextView
//        TextView tv = new TextView(ctx);
//        // 获取JSONArray数组元素的kindName属性
//        String kindName = getItem(position).getKindName();
//        // 设置TextView所显示的内容
//        tv.setText(kindName);
//        tv.setTextSize(20);
//        // 将TextView添加到LinearLayout中
//        linear.addView(tv);
//        container.addView(linear);
//        // 定义一个文本框来显示种类描述
//        TextView descView = new TextView(ctx);
//        descView.setPadding(30, 0, 0, 0);
//        String kindDesc = getItem(position).getKindDesc();
//        descView.setText(kindDesc);
//        descView.setTextSize(16);
//        container.addView(descView);
//        return container;
    }


    class ViewHolder {
        public ImageView imageView;//商品图像
        public TextView kindName;//商品种类
        public TextView kindDesc;//商品简介
    }
}
