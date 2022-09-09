package com.example.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.auth.R
import com.example.auth.vm.AuthViewModel
import com.example.domain.models.HomeResponse

@Composable
@Preview
fun AuthScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.adro_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        LoginScreen()
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (logo, loginContainer) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(logo) {
                    centerHorizontallyTo(parent)
                    linkTo(top = parent.top, bottom = parent.bottom, bias = 0.1f)
                }
        )

        Column(modifier = Modifier.constrainAs(loginContainer) {
            centerHorizontallyTo(parent)
            linkTo(top = logo.bottom, bottom = parent.bottom, bias = 0.6f)
        }) {
            Text(text = "Welcome")
            Text(text = "Abu dhabi visa holders")
            Text(text = "create account by entering email your emirates ID number")
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { "Enter your Emirates ID" },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.barcode_scanner),
                        contentDescription = ""
                    )
                }
            )
            val string = buildAnnotatedString {
                append("Have a account?")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W500,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(" Login")
                }
            }
            Text(text = string)
        }

        Divider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(text = "continue as guest")


    }
}

@Composable
fun AuthScreen() {
    val vm = hiltViewModel<AuthViewModel>()
    Surface(modifier = Modifier.background(Color.White)) {

    }

}

class SampleUserProvider : PreviewParameterProvider<HomeResponse.Data.Section> {
    override val values = sequenceOf(HomeResponse.Data.Section())
}

