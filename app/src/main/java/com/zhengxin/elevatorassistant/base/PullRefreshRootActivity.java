package com.zhengxin.elevatorassistant.base;


import com.code19.library.L;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by long on 2017/2/28.上下拉刷新加载
 */

public class PullRefreshRootActivity extends RootActivity implements PullToRefreshBase.OnRefreshListener2{
    public boolean isAdd = false;
    public int page = 1;
    protected  void getData(int page){

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {//下拉刷新
        isAdd = false;
        page = 1;
        getData(page);
        L.i("pullrefresh","onPullDownToRefresh");
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {//上拉加载
        isAdd = true;
        page++;
        getData(page);

        L.i("pullrefresh","onPullUpToRefresh");
    }
}
