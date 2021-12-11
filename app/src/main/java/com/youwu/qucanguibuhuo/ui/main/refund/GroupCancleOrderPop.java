package com.youwu.qucanguibuhuo.ui.main.refund;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.CenterPopupView;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.button.SmoothCheckBox;
import com.youwu.qucanguibuhuo.R;


public class GroupCancleOrderPop extends CenterPopupView {
    public GroupCancleOrderPop(@NonNull Context context) {
        super(context);
    }

    EditText etRmarks;

    SmoothCheckBox check;
    XUIAlphaButton submit;//提交
    XUIAlphaButton cancel;//取消
    private SubCallBack subCallBack;

    @Override
    protected int getImplLayoutId() {
        return R.layout.group_cancle_pop;
    }

    public void setSubCallBack(SubCallBack subCallBack) {
        this.subCallBack = subCallBack;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        etRmarks=findViewById(R.id.etRmarks);
        submit=findViewById(R.id.submit);
        cancel=findViewById(R.id.cancel);
        check=findViewById(R.id.check);
        check.setChecked(true);//默认选中

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = etRmarks.getText().toString();

                if (reason.isEmpty()) {
                    ToastUtils.showShort("请填写退款原因");
                    return;
                }
                if (subCallBack != null) {
                    subCallBack.sub(check.isChecked(), reason);
                }
                dismiss();
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface SubCallBack {
        void sub(boolean rollback_stock, String reason);

    }
}
