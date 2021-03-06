package com.youwu.qucanguibuhuo.utils_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.youwu.qucanguibuhuo.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeSelectView extends FrameLayout {

    private TextView startTime;
    private TextView endTime;
    private OnChangeListener onChangeListener;

    TimePickerView build;

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public TimeSelectView(@NonNull Context context) {
        this(context, null);
    }

    public TimeSelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeSelectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(LayoutInflater.from(getContext()).inflate(R.layout.time_choose, null));
        initView();
    }

    public void initView() {
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        ClickUtils.applyGlobalDebouncing(new View[]{startTime, endTime}, new OnClickListener() {
            @Override
            public void onClick(View view) {
                showTime((TextView) view);

            }
        });
        endTime.setText(getTime(new Date()));
        Calendar ca = Calendar.getInstance();
//        ca.add(Calendar.DATE, -1);
        startTime.setText(getTime(ca.getTime()));
    }

    public void showTime(TextView textView) {
        Calendar selectedDate = Calendar.getInstance();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(endDate.YEAR, 10);
         build = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//??????????????????
                textView.setText(getTime(date));
                if (onChangeListener != null) {
                    onChangeListener.onChange();
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                build.returnData();
                                build.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                build.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})//???????????????????????????????????????????????????
                .setContentTextSize(18)//??????????????????
                .setTitleSize(16)//??????????????????
                 .setLineSpacingMultiplier(2.0f)//??????????????????
                 .setItemVisibleCount(7)//????????????????????????
                .setOutSideCancelable(true)//???????????????????????????????????????????????????????????????
                .isCyclic(true)//??????????????????
                .setTitleBgColor(0xFFffffff)//?????????????????? Night mode
                .setBgColor(0xFFfafafa)//?????????????????? Night mode
//                .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//?????????1900-2100???
                .setDate(selectedDate)// ?????????????????????????????????????????????*/
                .setRangDate(startDate, endDate)//???????????????????????????
                .setLabel("???", "???", "???", "???", "???", "???")
                .isDialog(true)//??????????????????????????????
                .build();
        build.show();

    }

    private String getTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
        return simpleDateFormat.format(date);
    }

    public String getTimeRange() {
        Date date1 = TimeUtils.string2Date(startTime.getText().toString(), "yyyy???MM???dd");
        Date date2 = TimeUtils.string2Date(endTime.getText().toString(), "yyyy???MM???dd");
        String start = TimeUtils.date2String(date1, "yyyy/MM/dd HH:mm:ss");
        String end = TimeUtils.date2String(date2, "yyyy/MM/dd");
        return start + "~" + end + " 23:59:59";
    }

    public String getStart() {
        Date date1 = TimeUtils.string2Date(startTime.getText().toString(), "yyyy???MM???dd");
        return date1.getTime() / 1000 + "";
    }

    public String getEnd() {
        Date date2 = TimeUtils.string2Date(endTime.getText().toString(), "yyyy???MM???dd???");
        return date2.getTime() / 1000 + 86399 + "";
    }

    public interface OnChangeListener {
        void onChange();

    }


}
