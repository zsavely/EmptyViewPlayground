package com.szagurskii.radicalrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewSwitcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
  @BindView(R.id.rv_items) RecyclerView recyclerView;
  @BindView(R.id.vs) ViewSwitcher viewSwitcher;

  final BasicAdapter basicAdapter = new BasicAdapter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    basicAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override public void onChanged() {
        if (basicAdapter.getItemCount() == 0) {
          // Hide RecyclerView
          viewSwitcher.setDisplayedChild(0);
        } else {
          // Show RecyclerView
          viewSwitcher.setDisplayedChild(1);
        }
      }
    });
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(basicAdapter);
  }

  @OnClick(R.id.btn_add_one) void onItemAddOneClicked() {
    basicAdapter.addItems(Collections.singletonList(Item.createItem(UUID.randomUUID().toString())));
  }

  @OnClick(R.id.btn_add_ten) void onItemAddTenClicked() {
    basicAdapter.addItems(
        Arrays.asList(
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString()),
            Item.createItem(UUID.randomUUID().toString())
        )
    );
  }

  @OnClick(R.id.btn_remove_one) void onItemRemoveOneClicked() {
    basicAdapter.removeLastItem();
  }

  @OnClick(R.id.btn_remove_all) void onItemRemoveAllClicked() {
    basicAdapter.removeAll();
  }
}
