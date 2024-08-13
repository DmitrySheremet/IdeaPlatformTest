package com.ideaplatformtest.ui.features.itemlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideaplatformtest.R
import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.ui.theme.IdeaPlatformTestTheme
import com.ideaplatformtest.ui.util.toLocalFormat
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemCard(
  item: Item,
  onEditItemClick: () -> Unit = {},
  onDeleteItemClick: () -> Unit = {}
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 10.dp, bottom = 10.dp),
    elevation = CardDefaults.cardElevation(3.dp),
    shape = RoundedCornerShape(10.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = item.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            modifier = Modifier
              .size(32.dp)
              .padding(2.dp),
            onClick = onEditItemClick
          ) {
            Icon(
              imageVector = Icons.Filled.Edit,
              contentDescription = null,
              tint = Color(0xff6200ee)
            )

          }

          Spacer(modifier = Modifier.width(10.dp))

          IconButton(modifier = Modifier
            .size(32.dp)
            .padding(2.dp), onClick = onDeleteItemClick) {
            Icon(
              imageVector = Icons.Filled.Delete,
              contentDescription = null,
              tint = Color(0xffcc4100)
            )
          }
        }

      }

      Row(modifier = Modifier.fillMaxWidth()) {
        FlowRow(verticalArrangement = Arrangement.spacedBy((-15).dp, Alignment.Top)) {
          item.tags.forEach { tag ->
            SuggestionChip(
              modifier = Modifier.padding(3.dp),
              onClick = { /*TODO*/ },
              label = { Text(text = tag, fontWeight = FontWeight.Bold) }
            )
          }
        }
      }

      Row(modifier = Modifier.fillMaxWidth()) {
        Column(
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 5.dp)
        ) {
          Text(text = stringResource(id = R.string.label_instock), fontWeight = FontWeight.Bold)
          Text(text = if (item.amount > 0) item.amount.toString() else stringResource(id = R.string.label_outofstock))
        }

        Column(
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 5.dp)
        ) {
          Text(text = stringResource(R.string.label_time), fontWeight = FontWeight.Bold)
          Text(text = item.time.toLocalFormat())
//        time = Instant.ofEpochMilli(item.time.toLong()).toString()
        }
      }

    }
  }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun Preview() {
  IdeaPlatformTestTheme {
    ItemCard(
      Item(
        id = 1, name = "Samsung A23",
        tags = listOf("Телефон", "Телефон", "Телефон", "Телефон", "Телефон", "Телефон", "Телефон"),
        amount = 5,
        time = Date()
      )
    )
  }
}