package com.ideaplatformtest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ideaplatformtest.IpTestApp
import com.ideaplatformtest.R
import com.ideaplatformtest.ui.features.itemlist.EditItemDialog
import com.ideaplatformtest.ui.features.itemlist.ItemListUiAction
import com.ideaplatformtest.ui.features.itemlist.ItemListUiSideEffect
import com.ideaplatformtest.ui.features.itemlist.ItemListViewModel
import com.ideaplatformtest.ui.features.itemlist.ItemListViewModelFactoryImpl
import com.ideaplatformtest.ui.features.itemlist.ItemsLazyColumn
import com.ideaplatformtest.ui.shared.ActivityTitle
import com.ideaplatformtest.ui.shared.ConfirmDialog
import com.ideaplatformtest.ui.theme.IdeaPlatformTestTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import javax.inject.Inject

class MainActivity : ComponentActivity() {
  @Inject
  lateinit var itemListViewModelFactory: ItemListViewModelFactoryImpl

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    IpTestApp.appComponent.inject(this)
//    enableEdgeToEdge()
    setContent {
      val viewModel: ItemListViewModel = viewModel(factory = itemListViewModelFactory)
      IdeaPlatformTestTheme {
        viewModel.collectSideEffect { effect ->
          handleSideEffect(effect = effect)
        }
        MainContent()
      }
    }
  }

  private fun handleSideEffect(effect: ItemListUiSideEffect) {
    when (effect) {
      is ItemListUiSideEffect.ItemListUiSideEffectError ->
        Toast.makeText(this, effect.message ?: getString(R.string.msg_error), Toast.LENGTH_SHORT)
          .show()
    }
  }
}

@Composable
fun MainContent() {
  val viewModel: ItemListViewModel = viewModel()
  val state = viewModel.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()

  ) {
    ActivityTitle(title = stringResource(R.string.activity_title_itemslist))

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(start = 10.dp, end = 10.dp)
    ) {

      val onSearchChanged: (String) -> Unit =
        { newValue -> viewModel.action(ItemListUiAction.SearchItems(newValue)) }

      ItemsLazyColumn(
        modifier = Modifier
          .fillMaxSize(),
        items = state.value.items,
        searchText = state.value.filterText,
        onSearchChanged = onSearchChanged,
        onSearchClear = { viewModel.action(ItemListUiAction.SearchItems("")) },
        onEditItemClick = { item ->
          viewModel.action(ItemListUiAction.EditItem(item))
        },
        onDeleteItemClick = { item ->
          viewModel.action(ItemListUiAction.DeleteItemRequest(item))
        }
      )
    }

  }

  state.value.editItem?.let { item ->
    EditItemDialog(
      item = item,
      onPlusClick = { viewModel.action(ItemListUiAction.EditItemAddQuantity(1)) },
      onMinusClick = { viewModel.action(ItemListUiAction.EditItemAddQuantity(-1)) },
      onOkClick = { viewModel.action(ItemListUiAction.CommitItemUpdate) },
      onCancelClick = { viewModel.action(ItemListUiAction.EditItemCancel) })
  }

  state.value.deleteItem?.let {
    ConfirmDialog(
      title = stringResource(id = R.string.title_good_delete), message = stringResource(
        R.string.msg_delete_good_q
      ),
      onOkClick = { viewModel.action(ItemListUiAction.CommitItemDelete) },
      onCancelClick = { viewModel.action(ItemListUiAction.DeleteItemCancel) }
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
  IdeaPlatformTestTheme {
    MainContent()
  }
}