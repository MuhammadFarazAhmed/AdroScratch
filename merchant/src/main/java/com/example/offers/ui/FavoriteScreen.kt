package com.example.offers.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

//@Composable
//fun FavoriteScreen(vm: FavoriteViewModel = getViewModel()) {
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//
//        val lazyFavs = vm.favoriteList.collectAsLazyPagingItems()
//
//        LazyColumn {
//
//            items(lazyFavs) { fav ->
//                FavoriteItem(fav)
//            }
//            applyPagination(lazyFavs)
//        }
//
//    }
//}
//
//class FavProvider : PreviewParameterProvider<FavoriteResponse.Data.Outlet> {
//    override val values = sequenceOf(FavoriteResponse.Data.Outlet())
//}
//
//@Composable
//@Preview
//fun FavoriteItem(@PreviewParameter(FavProvider::class) fav: FavoriteResponse.Data.Outlet?) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize()
//            .drawBehind {
//                val strokeWidth = Stroke.DefaultMiter
//                val x = size.width - strokeWidth
//                drawLine(
//                    color = Color.LightGray,
//                    start = Offset(20f, 0f), //(0,0) at top-left point of the box
//                    end = Offset(x - 20, 0f), //top-right point of the box
//                    strokeWidth = strokeWidth
//                )
//            },
//    ) {
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Card(
//                modifier = Modifier
//                    .padding(12.dp)
//                    .height(75.dp)
//                    .width(75.dp),
//                border = BorderStroke(1.dp, Color.Gray),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                AsyncImage(model = fav?.merchantLogoUrl, contentDescription = "")
//            }
//            Column(
//                modifier = Modifier
//                    .padding(vertical = 12.dp)
//                    .fillMaxHeight(),
//                verticalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(text = fav?.merchantName ?: "")
//                Text(text = fav?.name ?: "", modifier = Modifier.padding(vertical = 6.dp))
//                Text(text = fav?.humanLocation ?: "")
//            }
//        }
//
//    }
//}
//
