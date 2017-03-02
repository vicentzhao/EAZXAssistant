package com.zhengxin.elevatorassistant.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.zhengxin.ealibrary.net.NetBean;
import com.zhengxin.ealibrary.net.RequestCallBack;
import com.zhengxin.ealibrary.net.XutilHttpHelp;
import com.zhengxin.ealibrary.utils.ToastUtil;
import com.zhengxin.ealibrary.view.PullToRefreashRecylcleView;
import com.zhengxin.elevatorassistant.R;
import com.zhengxin.elevatorassistant.adapter.CeshiAdapter;
import com.zhengxin.elevatorassistant.base.APIconfig;
import com.zhengxin.elevatorassistant.base.PullRefreshRootActivity;
import com.zhengxin.elevatorassistant.base.RootActivity;
import com.zhengxin.elevatorassistant.model.CeShi;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends PullRefreshRootActivity {

    @Bind(R.id.tv_btn)
    TextView tvBtn; //测试提交xinglong王兴隆
    @Bind(R.id.recViewforceshi)
    PullToRefreashRecylcleView recViewforceshi;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    private List<CeShi> mDatas = new ArrayList<>();
    private CeshiAdapter ceshiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recViewforceshi.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
        recViewforceshi.getRefreshableView().setLayoutManager(new LinearLayoutManager(this));
        recViewforceshi.getRefreshableView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ceshiAdapter = new CeshiAdapter(MainActivity.this, mDatas);
        recViewforceshi.getRefreshableView().setAdapter(ceshiAdapter);
        HttpCeshi();
        ceshiAdapter.setOnItemClickLitener(new CeshiAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showShort(mDatas.get(position).getName());
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recViewforceshi.setScrollingWhileRefreshingEnabled(true);
        recViewforceshi.setMode(PullToRefreshBase.Mode.BOTH);
        recViewforceshi.setOnRefreshListener(this);
    }

    @Override
    protected void getData(int page) {
        super.getData(page);
        HttpCeshi();
    }

    private void HttpCeshi() {
        RequestParams params = new RequestParams(APIconfig.IP +
                "/Service/ProvinceCityArea/GetCity");
        params.addBodyParameter("ProvinceCode", "37");
        XutilHttpHelp.getInstance().BaseInfoHttp(params, MainActivity.this, new RequestCallBack<NetBean<CeShi, CeShi>>() {

            @Override
            public void onSuccess(final NetBean<CeShi, CeShi> result) {
                mDatas = result.getDatas();
                List<CeShi> list = result.getDatas();
                if (mDatas != null) {
                    if (isAdd) {
                        if (list != null && list.size() > 0) {
                            mDatas.addAll(list);
                            ceshiAdapter.notifyDataSetChanged();
                        } else {
                            page--;
                            ToastUtil.showShort("没有更多数据了");
                        }
                    } else {
                        if (list != null && list.size() > 0) {
                            mDatas = list;
                            ceshiAdapter.setCeShis(mDatas);
                            ceshiAdapter.notifyDataSetChanged();
                        } else {
                            //未搜索到相关信息将adapter中的数据清空
                            List<CeShi> modelnull = new ArrayList<CeShi>();
                            ceshiAdapter.setCeShis(modelnull);
                            ceshiAdapter.notifyDataSetChanged();
//                          Alert.ShowInfo(getActivity(), "未搜索到相关信息");
                        }
                    }
                }
                recViewforceshi.onRefreshComplete();
            }
        });

    }


    @OnClick({R.id.tv_btn, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn: {

            }
            break;
            case R.id.tv_delete: {

            }
            break;
        }
    }
}
