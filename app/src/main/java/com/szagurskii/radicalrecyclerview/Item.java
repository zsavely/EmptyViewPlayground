package com.szagurskii.radicalrecyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Item {
  @NonNull abstract String uuid();

  @NonNull static Item createItem(@Nullable String uuid) {
    return new AutoValue_Item(String.valueOf(uuid));
  }
}
