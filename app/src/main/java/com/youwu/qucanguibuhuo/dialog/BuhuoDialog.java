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


public class BuhuoDialog extends CenterPopupView {

    EditText etNum;
    TextView tvName;

    TextView tvPackegeName;

    EditText tvNum;

    CabinetItemBean bean;

    public OnchangeInterface onchangeInterface;
    MyCustKeybords custKeybords;

    XUIAlphaButton determine;


    public void setOnchangeInterface(OnchangeInterface onchangeInterface) {
        this.onchangeInterface = onchangeInterface;
    }

    public BuhuoDialog(@NonNull Context context, CabinetItemBean bean) {
        super(context);
        this.bean = bean;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        etNum=findViewById(R.id.etNum);
        tvName=findViewById(R.id.tvName);
        tvPackegeName=findViewById(R.id.tvPackegeName);
        tvNum=findViewById(R.id.tvNum);
        determine=findViewById(R.id.determine);

        custKeybords = findViewById(R.id.custKeybords);

        etNum.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        tvNum.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);

        findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //取消系统软键盘
//        hideSoftInputMethod(etNum);
//        hideSoftInputMethod(tvNum);
//        custKeybords.bindEditText(etNum);
        etNum.setSelectAllOnFocus(true);
        tvNum.setSelectAllOnFocus(true);

        determine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String editNumber = etNum.getText().toString();
                if (editNumber.isEmpty()) {
                    ToastUtils.showShort("请输入补货数量");
                    return;
                }
                if (tvNum.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请输入补货后数量");
                    return;
                }
                bean.setEditNum(editNumber);
                bean.setEndNum(tvNum.getText().toString());
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
                    ToastUtils.showShort("请输入补货数量");
                    return;
                }
                if (tvNum.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请输入补货后数量");
                    return;
                }
                bean.setEditNum(editNumber);
                bean.setEndNum(tvNum.getText().toString());
                if (onchangeInterface!=null){
                    onchangeInterface.onChange();
                }
                dismiss();
            }
        });
        setData();

//        etNum.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b){
//                    custKeybords.bindEditText(etNum);
//                }
//
//            }
//        });
//        tvNum.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b){
//                    custKeybords.bindEditText(tvNum);
//                }
//            }
//        });

    }


    public void setNum() {
        try {
            tvNum.setText(Integer.parseInt(bean.getStock()) + Integer.parseInt(etNum.getText().toString()) + "");
        } catch (Exception e) {
            e.printStackTrace();
            tvNum.setText(bean.getStock());
        }
    }

    public void setData() {
        tvName.setText(bean.getCabinet_number());
        tvPackegeName.setText(bean.getPackage_name());
        etNum.setText(bean.getEditNum());
        tvNum.setText(bean.getEndNum());
        setNum();
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.h_dialog_buhuo;
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
