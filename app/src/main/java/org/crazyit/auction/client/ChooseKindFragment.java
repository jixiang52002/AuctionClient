package org.crazyit.auction.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import org.crazyit.BaseFragment;
import org.crazyit.auction.client.adapter.KindAdapter;
import org.crazyit.auction.client.bean.KindBean;
import org.crazyit.auction.client.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class ChooseKindFragment extends BaseFragment {
    public static final int CHOOSE_ITEM = 0x1008;
    Callbacks mCallbacks;
    Button bnHome;
    ListView kindList;
    List<KindBean> beanList=new ArrayList<>();
    KindAdapter kindAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater
            , ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_kind
                , container, false);
        bnHome = (Button) rootView.findViewById(R.id.bn_home);
        kindList = (ListView) rootView.findViewById(R.id.kindList);
        // 为返回按钮的单击事件绑定事件监听器
        bnHome.setOnClickListener(new HomeListener(getActivity()));

        // 使用ListView显示所有物品准种类
        kindAdapter=new KindAdapter(beanList
                , getActivity());
        kindList.setAdapter(kindAdapter);

        kindList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("kindName", beanList.get(position).getKindName());
                mCallbacks.onItemSelected(CHOOSE_ITEM, bundle);
            }
        });
        initData();
        return rootView;
    }

    private void initData() {
//        KindBean kindBean=new KindBean();
//        kindBean.setKindName("玩具");
//        kindBean.setKindName("可爱的孩子们最爱");
//        kindBean.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e!=null){
//                    LogUtils.loge(e.getMessage());
//                    activity.toast("插入数据失败："+e.getMessage());
//                }else{
//                    activity.toast(s);
//                }
//            }
//        });

        BmobQuery<KindBean> query=new BmobQuery<KindBean>();
        query.doSQLQuery("select * from KindBean", new SQLQueryListener<KindBean>() {
            @Override
            public void done(BmobQueryResult<KindBean> bmobQueryResult, BmobException e) {
                if(e==null){
                    if( bmobQueryResult.getResults()!=null&& bmobQueryResult.getResults().size()>0){
                        beanList.clear();
                        beanList.addAll(bmobQueryResult.getResults());
                        LogUtils.logd("获取数据成功");
                    }else{
                        beanList=new ArrayList<KindBean>();
                    }
                    kindAdapter.notifyDataSetChanged();
                }else{
                    LogUtils.loge("获取数据失败："+e.getMessage());
                    activity.toast(e.getMessage());
                }
            }
        });
    }

    // 当该Fragment被添加、显示到Activity时，回调该方法
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 如果Activity没有实现Callbacks接口，抛出异常
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException(
                    "ManageKindFragment所在的Activity必须实现Callbacks接口!");
        }
        // 把该Activity当成Callbacks对象
        mCallbacks = (Callbacks) activity;
    }

    // 当该Fragment从它所属的Activity中被删除时回调该方法
    @Override
    public void onDetach() {
        super.onDetach();
        // 将mCallbacks赋为null。
        mCallbacks = null;
    }
}