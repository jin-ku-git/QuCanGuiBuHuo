package com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.ui.adapter.SCDropDownRecycleAdapter;
import com.youwu.qucanguibuhuo.utils_view.DividerItemDecorations;


import java.util.ArrayList;


/**
 * Description:
 * Create by dance, at 2018/12/21
 */
public class MDPopupView extends PartShadowPopupView {
    public MDPopupView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.m_d_popupwindow;
    }

    RecyclerView recyclerView;
    private ArrayList<String> dataList =  new ArrayList<String>();
    @Override
    protected void onCreate() {
        super.onCreate();
        recyclerView = findViewById(R.id.recyclerView);


        SCDropDownRecycleAdapter adapter = new SCDropDownRecycleAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        //设置item的分割线
        if (recyclerView.getItemDecorationCount()==0) {
            recyclerView.addItemDecoration(new DividerItemDecorations(getContext(), DividerItemDecorations.VERTICAL));
        }

        adapter.setOnItemClickListener(new SCDropDownRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                if (mChoiceener != null) {
                    mChoiceener.onChoice(data);
                }
            }
        });
    }
    /**
     * 设置数据
     * @param list
     */
    public void setItemsData(ArrayList<String> list){
        dataList = list;

    }

    private OnChoiceener mChoiceener;
    //选择的回调
    public interface OnChoiceener {
        void onChoice(String data);
    }

    public void setOnChoiceener(OnChoiceener listener) {
        mChoiceener = listener;
    }


    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag","CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag","CustomPartShadowPopupView onDismiss");
    }
}
