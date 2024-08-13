package com.ideaplatformtest.ui.features.itemlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ideaplatformtest.R
import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.ui.shared.SearchView

@Composable
fun ItemsLazyColumn(
  modifier: Modifier = Modifier,
  items: List<Item>,
  searchText: String,
  onSearchChanged: (String) -> Unit,
  onSearchClear: () -> Unit,
  onEditItemClick: (Item) -> Unit,
  onDeleteItemClick: (Item) -> Unit
) {
  LazyColumn(modifier = Modifier.then(modifier)) {
    item {
      SearchView(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 10.dp, bottom = 10.dp),
        value = searchText,
        label = stringResource(R.string.filter_field_label),
        onValueChanged = onSearchChanged,
        onClear = onSearchClear
      )
    }

    items(items) { item ->
      ItemCard(
        item = item,
        onEditItemClick = {
          onEditItemClick(item)
        },
        onDeleteItemClick = {
          onDeleteItemClick(item)
        }
      )
    }
  }
}