package com.youwu.qucanguibuhuo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youwu.qucanguibuhuo.R;


/**
 * 2021/11/30
 */

public class DiyDialog {
    private Context context;
    private Dialog dialog;

    private TextView tv_dialog_content;
    private TextView tv_cancle;
    private TextView tv_ok;

    private String content;
    private String cancle;
    private String ok ;

    public DialogClickListener dialogClickListener;
    public void setDialogClickListener(DialogClickListener dialogClickListener){
        this.dialogClickListener=dialogClickListener;
    }

    public DiyDialog setContent(String content) {
        this.content = content;
        return this;
    }
    public DiyDialog setCancle(String cancle) {
        this.cancle = cancle;
        return this;
    }
    public DiyDialog setOk(String ok) {
        this.ok = ok;
        return this;
    }

    public DiyDialog(Context context){
        this.context=context;
    }
    public DiyDialog builder(){
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_choose, null);

        tv_dialog_content= (TextView) view.findViewById(R.id.tv_dialog_content);
        tv_cancle= (TextView) view.findViewById(R.id.tv_cancle);
        tv_ok= (TextView) view.findViewById(R.id.tv_ok);
        if(!TextUtils.isEmpty(content))tv_dialog_content.setText(content);
        if(!TextUtils.isEmpty(cancle))tv_cancle.setText(cancle);
        if(!TextUtils.isEmpty(ok))tv_ok.setText(ok);

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogClickListener!=null)dialogClickListener.cancle();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogClickListener!=null)dialogClickListener.ok();
            }
        });
        dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setContentView(view);
        //获取屏幕宽高
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int widths = display.widthPixels;
        int height = display.heightPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        //设置弹窗宽高
        layoutParams.width = (int) (widths*0.7);
        layoutParams.height = (int) (height * 0.3);

        view.setLayoutParams(layoutParams);


        return this;
    }
    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }


    public interface DialogClickListener{
        void cancle();
        void ok();
    }

}
