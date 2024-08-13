package com.ideaplatformtest.domain

import java.util.Date

data class Item(
  val id: Long,
  val name: String,
  val time: Date,
  val tags: List<String>,
  val amount: Long
)