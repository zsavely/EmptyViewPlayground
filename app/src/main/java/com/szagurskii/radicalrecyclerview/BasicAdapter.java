package com.szagurskii.radicalrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.ViewHolder> {
  private static final String TAG = "BasicAdapter";

  private final List<Item> items = new ArrayList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.d(TAG, String.format("onCreateViewHolder(), size = %1$s", items.size()));
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basic_item, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Log.d(TAG, String.format("onBindViewHolder(), size = %1$s", items.size()));
    final Item item = items.get(position);
    holder.uuidView.setText(item.uuid());
  }

  @Override public int getItemCount() {
    return items.size();
  }

  void addItems(@NonNull List<Item> items) {
    Log.d(TAG, "addItems()");
    final boolean shouldReCreate = this.items.isEmpty();
    final int positionStart = this.items.size();

    this.items.addAll(items);

    if (shouldReCreate) {
      notifyDataSetChanged();
    } else {
      notifyItemRangeInserted(positionStart, items.size());
    }
  }

  void removeLastItem() {
    if (items.size() > 0) {
      final int position = items.size() - 1;
      final boolean shouldReCreate = position == 0;
      items.remove(position);

      if (shouldReCreate) {
        notifyDataSetChanged();
      } else {
        notifyItemRemoved(position);
      }
    }
  }

  void removeAll() {
    items.clear();
    notifyDataSetChanged();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_uuid) TextView uuidView;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
