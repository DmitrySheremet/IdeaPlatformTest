package com.ideaplatformtest.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.outlineLight
import com.example.compose.outlineVariantLight
import com.example.compose.surfaceVariantLight
import com.ideaplatformtest.R

@Composable
fun SearchView(
  value: String,
  label: String,
  modifier: Modifier = Modifier,
  onValueChanged: (String) -> Unit = {},
  onClear: () -> Unit = {}
) {
  OutlinedTextField(
    modifier = Modifier.then(modifier),
    value = value,
    singleLine = true,
    onValueChange = onValueChanged,
    shape = RoundedCornerShape(5.dp),
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        modifier = Modifier
      )
    },
    trailingIcon = {
      if(value.isNotEmpty()) Icon(Icons.Filled.Clear,
        contentDescription = null,
        modifier = Modifier
          .clickable {
            onClear()
          }
      )
    },
    label = { Text(label) },
    colors = filterTextFieldColors()
  )
}

@Composable
private fun filterTextFieldColors() = TextFieldDefaults.colors(
  unfocusedContainerColor = surfaceVariantLight,
  unfocusedIndicatorColor = outlineLight,
  focusedContainerColor = surfaceVariantLight,
  focusedIndicatorColor = outlineVariantLight,
  focusedLeadingIconColor = outlineLight,
  unfocusedLeadingIconColor = outlineLight,
  focusedTrailingIconColor = outlineLight,
  unfocusedTrailingIconColor = outlineLight,
  focusedLabelColor = outlineLight,
  unfocusedLabelColor = outlineLight,

)

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun Preview() {
  val text by remember { mutableStateOf("") }
  Column(modifier = Modifier.fillMaxSize()) {
    SearchView(
      value = text,
      label = stringResource(id = R.string.filter_field_label),
      modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
      onValueChanged = { },
      onClear = { }
    )

  }
}