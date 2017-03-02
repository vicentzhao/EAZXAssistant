// Generated code from Butter Knife. Do not modify!
package com.zhengxin.elevatorassistant.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.zhengxin.elevatorassistant.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493000, "field 'tvBtn' and method 'onClick'");
    target.tvBtn = finder.castView(view, 2131493000, "field 'tvBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493002, "field 'recViewforceshi'");
    target.recViewforceshi = finder.castView(view, 2131493002, "field 'recViewforceshi'");
    view = finder.findRequiredView(source, 2131493001, "field 'tvDelete' and method 'onClick'");
    target.tvDelete = finder.castView(view, 2131493001, "field 'tvDelete'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492999, "field 'activityMain'");
    target.activityMain = finder.castView(view, 2131492999, "field 'activityMain'");
  }

  @Override public void unbind(T target) {
    target.tvBtn = null;
    target.recViewforceshi = null;
    target.tvDelete = null;
    target.activityMain = null;
  }
}
