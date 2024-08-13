package com.ideaplatformtest.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toLocalFormat(): String {
  return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)
}