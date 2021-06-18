package com.olhiim.keepaccounts.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.olhiim.keepaccounts.R;

/*
在记录页面弹出时间对话框
**/
public class SelectTimeDialog extends Dialog implements View.OnClickListener {

    EditText hourEt, minuteEt;
    Button ensureBtn, cancelBtn;
    DatePicker datePicker;

    public interface OnEnsureListener{
        public void onEnsure(String time, int year, int month, int day);
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public SelectTimeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time); //绑定dialog
        hourEt = findViewById(R.id.dialog_time_hour);
        minuteEt = findViewById(R.id.dialog_time_minute);
        ensureBtn = findViewById(R.id.dialog_time_btn_ensure);
        cancelBtn = findViewById(R.id.dialog_time_btn_cancel);
        datePicker = findViewById(R.id.dialog_time_dp);
        //添加监听事件
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        hideDatePickerHeader();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_time_btn_cancel:
                cancel();
                break;
            case R.id.dialog_time_btn_ensure:
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int dayOfMonth = datePicker.getDayOfMonth();
                String montStr = String.valueOf(month);
                if(month < 10) {
                    montStr = "0" + month;
                }

                String dayStr = String.valueOf(dayOfMonth);
                if(dayOfMonth < 10) {
                    dayStr = "0" + dayOfMonth;
                }

                //获取输入的小时和分钟
                String hourStr = hourEt.getText().toString();
                String minuteStr = minuteEt.getText().toString();
                int hour = 0;
                if (!TextUtils.isEmpty(hourStr)) {
                    hour = Integer.parseInt(hourStr);
                    hour = hour % 24;
                }
                int minute = 0;
                if (!TextUtils.isEmpty(minuteStr)) {
                    minute = Integer.parseInt(minuteStr);
                    minute = minute % 60;
                }

                hourStr = String.valueOf(hour);
                minuteStr = String.valueOf(minute);

                if(hour < 10) {
                    hourStr = "0" + hour;
                }
                if(minute < 10) {
                    minuteStr = "0" + minute;
                }
                String timeFormat = year + "年 " + montStr + "月 " + dayStr + "日 " + hourStr + ":" + minuteStr;

                if (onEnsureListener != null) {
                    onEnsureListener.onEnsure(timeFormat, year, month,dayOfMonth);
                }
                cancel();
                break;
        }
    }

    //隐藏DatePicker的头布局
    private void hideDatePickerHeader() {
        ViewGroup rootView = (ViewGroup) datePicker.getChildAt(0);
        if (rootView == null) {
            return;
        }
        View headerView = rootView.getChildAt(0);
        if (headerView == null) {
            return;
        }

        //5.0+  下面的name改为:day_picker_selector_layout
        /*int headerId = getContext().getResources().getIdentifier("day_picker_selector_layout", "id", "android");
        if (headerId == headerView.getId()) {
            headerView.setVisibility(View.GONE);

            ViewGroup.LayoutParams layoutParamsRoot = rootView.getLayoutParams();
            layoutParamsRoot.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            rootView.setLayoutParams(layoutParamsRoot);

            ViewGroup animator = (ViewGroup) rootView.getChildAt(1);
            ViewGroup.LayoutParams layoutParamsAnimator = animator.getLayoutParams();
            layoutParamsAnimator.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            animator.setLayoutParams(layoutParamsAnimator);

            View child = animator.getChildAt(0);
            ViewGroup.LayoutParams layoutParamsChild = child.getLayoutParams();
            layoutParamsChild.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            child.setLayoutParams(layoutParamsChild);
            return;
        }*/

        //6.0+   name:date_picker_header
        int headerId = getContext().getResources().getIdentifier("date_picker_header", "id", "android");
        if (headerId == headerView.getId()) {
            headerView.setVisibility(View.GONE);

        }
    }
}
