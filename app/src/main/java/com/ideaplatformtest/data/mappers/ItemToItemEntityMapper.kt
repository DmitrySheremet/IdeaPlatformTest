package com.ideaplatformtest.data.mappers

import com.google.gson.Gson
import com.ideaplatformtest.data.items.ItemEntity
import com.ideaplatformtest.domain.Item
import java.util.Date

fun Item.toItemEntity(): ItemEntity {
  val gson = Gson()
  return ItemEntity(
    id = id,
    name = name,
    time = time.time,
    tags = gson.toJson(tags),
    amount = amount
  )
}