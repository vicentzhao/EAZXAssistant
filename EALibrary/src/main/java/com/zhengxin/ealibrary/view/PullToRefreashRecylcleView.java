package com.zhengxin.ealibrary.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by long on 2017/3/1.
 */

public class PullToRefreashRecylcleView extends PullToRefreshBase<RecyclerView>{
    public PullToRefreashRecylcleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreashRecylcleView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreashRecylcleView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    public PullToRefreashRecylcleView(Context context) {
        super(context);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView view = new RecyclerView(context, attrs);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        int lastVisiblePosition = getRefreshableView().getChildAdapterPosition(getRefreshableView().getChildAt(getRefreshableView().getChildCount() -1));
        if (lastVisiblePosition >= getRefreshableView().getAdapter().getItemCount()-1) {
            return getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1).getBottom() <= getRefreshableView().getBottom();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (getRefreshableView().getChildCount() <= 0)
            return true;
        int firstVisiblePosition = getRefreshableView().getChildAdapterPosition(getRefreshableView().getChildAt(0));
        if (firstVisiblePosition == 0)
            return getRefreshableView().getChildAt(0).getTop() == getRefreshableView().getPaddingTop();
        else
            return false;
    }
}
