package com.youwu.qucanguibuhuo.jianpan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ClickUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.util.KeyboardUtils;
import com.xuexiang.xui.adapter.recyclerview.GridDividerItemDecoration;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.jianpan.adapter.BaseAdapter;
import com.youwu.qucanguibuhuo.jianpan.adapter.IViewHolder;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MyCustKeybords extends FrameLayout {

    RecyclerView recyView;

    View layoutDel;

    FrameLayout layoutSystem;

    FrameLayout btnConfirm;
    private BaseAdapter<String> adapter;
    private EditText editText;
    private OnKeyBoradConfirm listener;
    private boolean needSystem;

    public void setListener(OnKeyBoradConfirm listener) {
        this.listener = listener;
    }

    public MyCustKeybords(Context context) {
        this(context, null);
    }

    public MyCustKeybords(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustKeybords(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.in_MyCustKeybords);
        needSystem = typedArray.getBoolean(R.styleable.in_MyCustKeybords_in_needSystem, true);
        typedArray.recycle();
        init();
    }

    public void init() {
        View keyBords = LayoutInflater.from(getContext()).inflate(R.layout.in_view_keybords, this);

        recyView=findViewById(R.id.recy_view);
        layoutDel=findViewById(R.id.layout_del);
        layoutSystem=findViewById(R.id.layout_system);
        btnConfirm=findViewById(R.id.btn_confirm);

        layoutSystem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText != null) {
                    editText.setFocusableInTouchMode(true);
                    editText.requestFocus();
                    KeyboardUtils.showSoftInput(editText);
                }
            }
        });
        //??????
        layoutDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText != null)
                    delete();
            }
        });

        layoutSystem.setVisibility(needSystem ? VISIBLE : GONE);
        ClickUtils.applyGlobalDebouncing(btnConfirm, new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onConfirm();
                }
            }
        });

    }

    public void bindEditText(EditText editText) {
        this.editText = editText;
        if (editText != null) {
            editText.setOnEditorActionListener(editorActionListener);
        }
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (listener != null) {
                    listener.onConfirm();
                }
            }
            return false;
        }
    };


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int itemHeight = (getMeasuredHeight() - 2) / 4;
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
        adapter = new BaseAdapter<String>(R.layout.in_item_keybords) {
            @Override
            protected void convert(@NotNull IViewHolder helper, String item) {
                View itemView = helper.itemView;
                itemView.setLayoutParams(params);
                helper.setText(R.id.tv_name, item);
            }


        };
        recyView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyView.addItemDecoration(new GridDividerItemDecoration(getContext(), 3, 1, Color.parseColor("#D8D8D8")));
        adapter.bindToRecyclerView(recyView);
        initData(1);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@androidx.annotation.NonNull BaseQuickAdapter<?, ?> ada, @androidx.annotation.NonNull View view, int position) {
                if (editText != null) {
                    inset(adapter.getItem(position));
                }
            }
        });

    }


    public void inset(String text) {
        int index = editText.getSelectionStart();//????????????????????????
        int selectionEnd = editText.getSelectionEnd();
        Editable edit = editText.getEditableText();//??????EditText?????????
        if (selectionEnd > index) {
            edit.replace(index, selectionEnd, "");
            inset(text);
            return;
        }

        if (index < 0 || index >= edit.length()) {
            edit.append(text);
        } else {
            edit.insert(index, text);

        }
    }

    /**
     * ??????????????????????????????
     */
    public void delete() {
        int index = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        Editable editable = editText.getText();
        if (selectionEnd > index) {
            editable.delete(index, selectionEnd);
        } else {
            if (index <= 0) {
                return;
            }
            editable.delete(index - 1, index);
        }
    }

    private void initData(int type) {
        List<String> list = new ArrayList<>();
        if (type == 1) {
            for (int i = 1; i < 10; i++) {
                //??????1-9
                list.add(String.valueOf(i));
            }
            list.add("-");
            list.add("0");
            list.add(".");
        }
        adapter.setList(list);
    }



    public interface OnKeyBoradConfirm {
        void onConfirm();

    }
}
