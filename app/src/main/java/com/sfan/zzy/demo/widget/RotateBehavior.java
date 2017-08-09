package com.sfan.zzy.demo.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 * 自定义behavior
 * CoordinatorLayout功能如此强大，而他的神奇之处在于Behavior对象，CoordinatorLayout自己并不控制View，所有的控制权都在Behavior。前面写到了FloatingActionButton.Behavior，AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior。 AppBarLayout中有两个Behavior，一个是拿来给它自己用的，另一个是拿来给它的兄弟结点用的。这些Behavior实现了复杂的控制功能。系统的Behavior毕竟有限，我们可以通过自定义的方式来实现自己的Behavior。
 通过 CoordinatorLayout.Behavior(YourView.Behavior.class) 来定义自己的Behavior，并在layout 文件中设置 app:layout_behavior=”com.example.app.YourView$Behavior” 来达到效果。
 自定义Behavior 需要重写两个方法：
 public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
 public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
 如下面的例子，实现了点击FloatingActionButton点击旋转90度，并适配Snackbar。
 */

public class RotateBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public RotateBehavior() {
    }

    public RotateBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = getFabTranslationYForSnackbar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        child.setRotation(-90 * percentComplete);
        child.setTranslationY(translationY);
        return false;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent, FloatingActionButton fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        return minOffset;
    }
}