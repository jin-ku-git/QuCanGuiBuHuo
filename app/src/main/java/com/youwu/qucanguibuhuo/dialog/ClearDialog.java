package com.youwu.qucanguibuhuo.dialog;

import android.content.Context;
import android.os.Build;

import android.text.InputType;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.dialog.bean.CabinetItemBean;
import com.youwu.qucanguibuhuo.jianpan.MyCustKeybords;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ClearDialog extends CenterPopupView {

    EditText etNum;

    TextView tvName;

    TextView tvPackegeName;

    CabinetItemBean bean;

    public OnchangeInterface onchangeInterface;
    //自定义键盘
    MyCustKeybords custKeybords;

    XUIAlphaButton determine;

    public void setOnchangeInterface(OnchangeInterface onchangeInterface) {
        this.onchangeInterface = onchangeInterface;
    }

    public ClearDialog(@NonNull Context context, CabinetItemBean bean) {
        super(context);
        this.bean = bean;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

//        hideSoftInputMethod(etNum);

        etNum=findViewById(R.id.etNum);
        tvName=findViewById(R.id.tvName);
        tvPackegeName=findViewById(R.id.tvPackegeName);
        custKeybords=findViewById(R.id.custKeybords);

        determine=findViewById(R.id.determine);

        custKeybords.bindEditText(etNum);
        //取消系统软键盘
//        hideSoftInputMethod(etNum);
        etNum.setSelectAllOnFocus(true);

        findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        determine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String editNumber = etNum.getText().toString();
                if (editNumber.isEmpty()) {
                    ToastUtils.showShort("请输入清货数量");
                    return;
                }
                bean.setClearNum(editNumber);
                if (onchangeInterface!=null){
                    onchangeInterface.onChange();
                }
                dismiss();
            }
        });
        custKeybords.setListener(new MyCustKeybords.OnKeyBoradConfirm() {
            @Override
            public void onConfirm() {
                String editNumber = etNum.getText().toString();
                if (editNumber.isEmpty()) {
                    ToastUtils.showShort("请输入清货数量");
                    return;
                }
                bean.setClearNum(editNumber);
                if (onchangeInterface!=null){
                    onchangeInterface.onChange();
                }
                dismiss();
            }
        });
        setData();

    }


    public void setData() {
        tvName.setText(bean.getCabinet_number());
        tvPackegeName.setText(bean.getPackage_name());
        etNum.setText(bean.getClearNum());
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.h_dialog_clear;
    }

    // 隐藏系统键盘
    private void hideSoftInputMethod(EditText ed) {

        int currentVersion = Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if (methodName == null) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }
    }

    public interface OnchangeInterface {
        void onChange();
    }


}
