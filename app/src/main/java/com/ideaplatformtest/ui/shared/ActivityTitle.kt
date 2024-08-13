package com.ideaplatformtest.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.systemBarColor
import com.ideaplatformtest.R

@Composable
fun ActivityTitle(title: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(color = systemBarColor),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      modifier = Modifier.padding(top = 20.dp, bottom = 20.dp), text = title, fontSize = 22.sp
    )
  }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun Preview() {
  ActivityTitle(title = stringResource(R.string.activity_title_itemslist))
}
