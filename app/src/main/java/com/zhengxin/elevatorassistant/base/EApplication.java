package com.zhengxin.elevatorassistant.base;

import android.app.Application;
import android.content.Context;

import com.zhengxin.ealibrary.core.LibraryApplication;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/23.
 */

public class EApplication extends Application {
    private static EApplication mApplication;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        EApplication.context = mApplication;
        LibraryApplication.init(mApplication);
        x.Ext.init(mApplication);
    }
}
