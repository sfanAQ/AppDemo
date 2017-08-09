package com.sfan.zzy.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Snackbar.make(fab, "FAB", Snackbar.LENGTH_LONG).setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里的单击事件代表点击消除Action后的响应事件

            }
        }).show();
    }
}
