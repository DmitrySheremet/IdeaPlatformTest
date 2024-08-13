package com.ideaplatformtest.ui.shared

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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ideaplatformtest.R

@Composable
fun ConfirmDialog(
  title: String,
  message: String,
  onOkClick: () -> Unit = {},
  onCancelClick: () -> Unit = {},
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
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Rounded.Warning,
            contentDescription = null
          )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
          Text(
            text = title,
            fontSize = 24.sp,
          )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
          Text(text = message, fontSize = 14.sp)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          TextButton(modifier = Modifier.padding(8.dp), onClick = onCancelClick) {
            Text(text = stringResource(R.string.button_label_no))
          }

          TextButton(modifier = Modifier.padding(8.dp), onClick = onOkClick) {
            Text(text = stringResource(R.string.button_label_yes))
          }

        }

      }
    }
  }
}