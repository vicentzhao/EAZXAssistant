package com.zhengxin.elevatorassistant.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.TimePickerView;
import com.zhengxin.ealibrary.view.SweetAlertDialog;
import com.zhengxin.elevatorassistant.R;

import java.util.Date;

/**
 * Created by long on 2017/2/22. 基类
 */

public class RootActivity extends Activity{
    private LinearLayout rootView;
    private View contentView;
    private TimePickerView pickerView;//时间选择器
    private SweetAlertDialog dialog;
    private Activity me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (LinearLayout) getLayoutInflater().inflate(R.layout.rootview, null);
        me=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        rootView.addView(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        super.setContentView(rootView);
    }
    /**
     * @param context
     * @param type
     * @param listener pickview选择时间
     */
    public void SelectTimeForPickView(Context context, TimePickerView.Type type, final OnSelectTimeListener listener){
        pickerView=new TimePickerView(context,type);
        pickerView.setTime(new Date());
        pickerView.setCyclic(false);
        pickerView.setCancelable(true);
        pickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                listener.onTimeSelect(date);
            }
        });
        CloseSoftInput();
        pickerView.show();
    }
    public interface OnSelectTimeListener{
        public void onTimeSelect(Date date);
    }
    /**
     * 缓冲图标
     * @param show
     */
    protected void Loading(boolean show) {
        if (dialog != null) {
        } else {
            dialog = new SweetAlertDialog(me);
        }
        if (show) {
            dialog.setTitleText("请稍候...");
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }
    /**
     * 关闭软键盘
     */
    private void CloseSoftInput(){
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
