package com.example.adro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    toolbarTitle: String,
    searchText: String,
    isBackIconShown: Boolean = true,
    isSearchBarShown: Boolean = true,
    onCrossClicked: () -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {

        Toolbar(toolbarTitle,isBackIconShown) {

        }

        if (isSearchBarShown)
            SearchBar(searchText, { onCrossClicked() }, { onQueryChange(it) })

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(
    text: String,
    trailingIconClicked: () -> Unit,
    onQueryChange: (String) -> Unit
) {

    SearchBar(
        query = text,
        colors = SearchBarDefaults.colors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp),
        active = false,
        onSearch = {
        },
        onActiveChange = {
        },
        placeholder = {
            Text(text = "What are you looking for?")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            if (text.isNotEmpty())
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier.clickable { trailingIconClicked() })

        },
        onQueryChange = {
            onQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 22.dp)
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun SearchViewPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchBar(query = "mak",
                onQueryChange = {},
                onSearch = {},
                active = true,
                onActiveChange = {}) {
            }
        }
    }
}