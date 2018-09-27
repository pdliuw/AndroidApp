package com.air.androidapp.component.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.air.androidapp.R;
import com.air.base.AppCommonActivity;
import com.air.ui.adapter.OnItemClickListener;
import com.air.ui.adapter.RecyclerAdapter;
import com.air.ui.adapter.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCommonActivity {

    private RecyclerView mRecyclerView;
    private List<Item> mRecyclerDatas;

    @Override
    protected int inflateContentViewById() {
        return R.layout.dialog_activity;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerDatas = new ArrayList<>();


        mRecyclerView.setAdapter(new RecyclerAdapter<Item>(R.layout.dialog_recycler_item, mRecyclerDatas, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Class targetClass = mRecyclerDatas.get(position).clas;
                startActivityTransition(new Intent(DialogActivity.this, targetClass));
            }
        }) {
            @Override
            protected void onBindHolder(RecyclerHolder holder, Item model, int position) {

                Button btn = holder.getViewById(R.id.dialog_item_btn);
                btn.setText(model.name);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {

    }

    public class Item {

        private String name;
        private Class clas;

        public Item(String name, Class clas) {
            this.clas = clas;
            this.name = name;
        }
    }
}
