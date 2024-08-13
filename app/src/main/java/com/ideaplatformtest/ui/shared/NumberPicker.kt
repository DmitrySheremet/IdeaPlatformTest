package com.ideaplatformtest.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.primaryLight
import com.ideaplatformtest.R

@Composable
fun NumberPicker(value: String, onPlusClick: () -> Unit = {}, onMinusClick: () -> Unit = {}) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    MinusPickerButton(onClick = onMinusClick)
    Text(text = value, fontSize = 24.sp, modifier = Modifier.padding(horizontal = 15.dp))
    PlusPickerButton(onClick = onPlusClick)
  }
}

@Composable
private fun MinusPickerButton(onClick: () -> Unit) {
  IconButton(onClick = onClick) {
    Icon(
      modifier = Modifier.size(36.dp),
      painter = painterResource(id = R.drawable.minus_circle_outline_24),
      contentDescription = null,
      tint = primaryLight
    )
  }

}

@Composable
private fun PlusPickerButton(onClick: () -> Unit) {
  IconButton(onClick = onClick) {
    Icon(
      modifier = Modifier.size(36.dp),
      painter = painterResource(id = R.drawable.plus_circle_outline_24),
      contentDescription = null,
      tint = primaryLight
    )
  }

}

@Composable
@Preview(showBackground = true)
private fun Preview() {
  NumberPicker(value = "10")
}