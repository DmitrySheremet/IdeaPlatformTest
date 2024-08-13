package com.ideaplatformtest.ui.features.itemlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ideaplatformtest.R
import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.ui.shared.NumberPicker
import java.util.Date

@Composable
fun EditItemDialog(
  item: Item,
  onOkClick: () -> Unit = {},
  onCancelClick: () -> Unit = {},
  onPlusClick: () -> Unit = {},
  onMinusClick: () -> Unit = {}
) {
  Dialog(onDismissRequest = { }) {
    Card(shape = RoundedCornerShape(20.dp)) {
      Column(modifier = Modifier.padding(20.dp)) {
        Row(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            modifier = Modifier.size(26.dp),
            imageVector = Icons.Default.Settings,
            contentDescription = null
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
          Text(
            text = stringResource(R.string.title_goods_quantity),
            fontSize = 24.sp,
          )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
          NumberPicker(value = item.amount.toString(), onPlusClick = onPlusClick, onMinusClick = onMinusClick)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          TextButton(modifier = Modifier.padding(8.dp), onClick = onCancelClick) {
            Text(text = stringResource(R.string.button_label_cancel))
          }

          TextButton(modifier = Modifier.padding(8.dp), onClick = onOkClick) {
            Text(text = stringResource(R.string.button_label_accept))
          }

        }

      }
    }
  }
}

@Composable
@Preview
private fun Preview() {
  EditItemDialog(
    item = Item(
      id = 1,
      name = "Phone",
      time = Date(),
      tags = listOf("Телефон"),
      amount = 5
    )
  )
}