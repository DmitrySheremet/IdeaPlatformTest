package com.ideaplatformtest.data.mappers

import com.google.gson.Gson
import com.ideaplatformtest.data.items.ItemEntity
import com.ideaplatformtest.domain.Item
import java.util.Date


fun ItemEntity.toItem(): Item {
  val gson = Gson()
  return Item(
    id = id, name = name,
    time = Date(time),
    tags = gson.fromJson<List<String>>(tags, List::class.java),
    amount = amount
  )
}