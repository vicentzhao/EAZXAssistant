package com.zhengxin.ealibrary.net;


import android.content.Context;

import com.code19.library.L;
import com.google.gson.Gson;
import com.zhengxin.ealibrary.core.LibraryApplication;
import com.zhengxin.ealibrary.view.SweetAlertDialog;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;
public class XutilHttpHelp {
	private XutilHttpHelp() {}
	private static XutilHttpHelp single=null;
	private static  Context mContext;

	/**
	 * 上下文对象
	 */


	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	private SweetAlertDialog dialog;//加载框
	public HttpMethod method=HttpMethod.POST;
	//静态工厂方法
	public static XutilHttpHelp getInstance() {
		if (single == null) {
			single = new XutilHttpHelp();
		}
		return single;
	}
	private Callback.Cancelable cancelable;

	/**
	 * @param Mparams
	 * @param requestCallBack
	 * @param <T>  若不设置请求方式，默认为post请求
	 * @return
	 */
	public <T> Callback.Cancelable BaseInfoHttp(final RequestParams Mparams,final Context context,
													final RequestCallBack<T> requestCallBack) {
		mContext=context;
		Mparams.setConnectTimeout(20 * 1000);
		cancelable = x.http().request(method, Mparams, new Callback.ProgressCallback<String>() {
			@Override
			public void onWaiting() {

			}
			@Override
			public void onStarted() {
				boolean networkConnected = NetworkUtil.isNetworkConnected(LibraryApplication.getContext());
				if (!networkConnected) {
					cancelable.cancel();
					requestCallBack.onError(null, "");
				} else {
					Loading(true);
					requestCallBack.onStarted();
				}
			}

			@Override
			public void onLoading(long total, long current, boolean isDownloading) {
				requestCallBack.onLoading(total, current, isDownloading);
			}

			@Override
			public void onSuccess(String result) {
				try {
					L.d(result);
					if (String.class != requestCallBack.mType) {
						Object o = new Gson().fromJson(result, requestCallBack.mType);
						if (o == null) {
							          requestCallBack.onError(null, "response null");
							return;
						}
						requestCallBack.onSuccess((T) o);
					} else {
						requestCallBack.onSuccess((T) result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					requestCallBack.onError(e, e.getMessage());
				}
			}
			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				L.e(ex == null ? Mparams.toString() : ex.getMessage() + ":" + Mparams.toString());
				requestCallBack.onError(new Exception(ex), "");

			}
			@Override
			public void onCancelled(CancelledException cex) {
				requestCallBack.onCancelled();
			}
			@Override
			public void onFinished() {
				requestCallBack.onFinished();
				Loading(false);
			}
		});
		 return  cancelable;
	}
	/**
	 * 缓冲图标
	 * @param show
	 */
	protected void Loading(boolean show) {
		if (dialog != null) {
		} else {
			dialog = new SweetAlertDialog(mContext);
		}
		if (show) {
			dialog.setTitleText("请稍候...");
			dialog.show();
		} else {
			dialog.dismiss();
		}
	}
	}