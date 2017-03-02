package com.zhengxin.ealibrary.core;

import android.content.Context;

/**
 *Library上下文环境
 * 
 */
public class LibraryApplication {
    private static Context context;
    private static String token;

    public static void init(Context context) {
        LibraryApplication.context = context;
    }

    public static Context getContext() {
        return context;
    }

    public static void setToken(String token) {
        LibraryApplication.token = token;
    }

    public static String getToken() {
        return token;
    }
}
