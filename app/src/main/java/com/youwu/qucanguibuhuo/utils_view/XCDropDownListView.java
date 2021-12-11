package com.youwu.qucanguibuhuo.utils_view;


import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.ui.adapter.SCDropDownRecycleAdapter;

import me.goldze.mvvmhabit.utils.KLog;

@SuppressLint("NewApi")
/**
 * 下拉列表框控件
 * @author caizhiming
 *
 */
public class XCDropDownListView extends LinearLayout{

    private TextView editText;
    private ImageView imageView;
    private RelativeLayout compound;

    private PopupWindow popupWindow = null;
    private ArrayList<String> dataList =  new ArrayList<String>();
    private View mView;

    private int Type=1;
    public XCDropDownListView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public XCDropDownListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        // TODO Auto-generated constructor stub
    }
    public XCDropDownListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initView();
    }

    public void initView(){
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        View view  = layoutInflater.inflate(R.layout.dropdownlist_view, this,true);
        editText= (TextView)findViewById(R.id.text);
        imageView = (ImageView)findViewById(R.id.btn);
         compound=findViewById(R.id.compound);
        compound.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.d("1");
                if(popupWindow == null){
                    compound.setEnabled(false);

                    showPopWindow();
                    //旋转90°
                    imageView.animate().rotation(90);

                }else {
                    Type=1;
                    closePopWindow();

                    //回正
                    imageView.animate().rotation(0);

                }
            }
        });

    }
    /**
     * 打开下拉列表弹窗
     */
    private void showPopWindow() {
        // 加载popupWindow的布局文件
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        View contentView  = layoutInflater.inflate(R.layout.dropdownlist_popupwindow, null,false);
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);

        SCDropDownRecycleAdapter   adapter = new SCDropDownRecycleAdapter(getContext(), dataList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        //设置item的分割线
        if (recyclerView.getItemDecorationCount()==0) {
            recyclerView.addItemDecoration(new DividerItemDecorations(getContext(), DividerItemDecorations.VERTICAL));
        }
        popupWindow = new PopupWindow(contentView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
        //获取焦点
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(this);
        //弹窗出现外部为阴影
        WindowManager.LayoutParams attributes = ((Activity) getContext()).getWindow().getAttributes();
        attributes.alpha = 0.5f;
        ((Activity) getContext()).getWindow().setAttributes(attributes);
        //弹窗取消监听 取消之后恢复阴影
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = ((Activity) getContext()).getWindow().getAttributes();
                attributes.alpha = 1;
                ((Activity) getContext()).getWindow().setAttributes(attributes);
                //回正
                imageView.animate().rotation(0);

                closePopWindow();
                Type=2;
                KLog.d("2");
            }
        });


        adapter.setOnItemClickListener(new SCDropDownRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                editText.setText(data);
                if (mChoiceener != null) {
                    mChoiceener.onChoice(data);
                }
                //回正
                imageView.animate().rotation(0);
                closePopWindow();
            }
        });
    }



    private OnChoiceener mChoiceener;
    //选择的回调
    public interface OnChoiceener {
        void onChoice(String data);
    }

    public void setOnChoiceener(OnChoiceener listener) {
        mChoiceener = listener;
    }

    /**
     * 关闭下拉列表弹窗
     */
    private void closePopWindow(){
        compound.setEnabled(true);
        popupWindow.dismiss();
        popupWindow = null;
    }
    /**
     * 设置数据
     * @param list
     */
    public void setItemsData(ArrayList<String> list){
        dataList = list;
        editText.setText(list.get(0).toString());
    }


}