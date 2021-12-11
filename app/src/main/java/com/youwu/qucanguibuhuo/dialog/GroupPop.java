package com.youwu.qucanguibuhuo.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.core.CenterPopupView;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.dialog.bean.TableItemBean;
import com.youwu.qucanguibuhuo.jianpan.adapter.BaseAdapter;
import com.youwu.qucanguibuhuo.jianpan.adapter.IViewHolder;


import org.jetbrains.annotations.NotNull;

import java.util.List;


public class GroupPop extends CenterPopupView {

    RecyclerView recyclerView;

    ImageView ivClose;
    List<TableItemBean> data;
    ClickInterface clickInterface;

    public void setClickInterface(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public GroupPop(@NonNull Context context, List<TableItemBean> data) {
        super(context);
        this.data=data;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.m_dialog_group;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        recyclerView=findViewById(R.id.recyclerView);
        ivClose=findViewById(R.id.iv_close);
        //关闭
        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> ad, @NonNull View view, int position) {
                if (clickInterface!=null){
                    clickInterface.click(position,adapter.getItem(position));
                }

            }
        });
        adapter.setList(data);
    }

    BaseAdapter<TableItemBean> adapter = new BaseAdapter<TableItemBean>(R.layout.m_dialog_item_group) {
        @Override
        protected void convert(@NotNull IViewHolder iViewHolder, TableItemBean s) {
            iViewHolder.setText(R.id.tvName,s.getCabinet_number());
        }
    };

    public interface ClickInterface{
        void click(int position, TableItemBean bean);

    }
}
