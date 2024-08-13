package com.ideaplatformtest.data.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String,
  val time: Long,
  val tags: String,
  val amount: Long
)
