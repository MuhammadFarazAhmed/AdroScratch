package com.example.auth.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.adro.common.HexToJetpackColor
import com.example.adro.theme.Emad
import com.example.auth.R
import com.example.domain.models.HomeResponse

@Composable
fun AuthScreen(onBackClick: () -> Unit) {
    BackHandler {
        onBackClick()
    }
    Surface(modifier = Modifier.background(Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.adro_image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        EMIDScreen()
    }

}

@Composable
@Preview
fun AuthScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.adro_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        EMIDScreen()
    }
}

@Composable
fun EMIDScreen() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (logo, loginContainer) = createRefs()

        Image(painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(logo) {
                    centerHorizontallyTo(parent)
                    linkTo(top = parent.top, bottom = parent.bottom, bias = 0.1f)
                })

        Column(modifier = Modifier.constrainAs(loginContainer) {
            centerHorizontallyTo(parent)
            linkTo(top = logo.bottom, bottom = parent.bottom, bias = 0.6f)
        }) {
            Text(text = "Welcome", color = Color.White)
            Text(text = "Abu dhabi visa holders", color = Color.White)
            Text(
                text = "create account by entering email your emirates ID number",
                color = Color.White,
                fontFamily = Emad
            )
            TextField(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(4.dp)),
                value = "",
                onValueChange = { it },
                placeholder = { "Enter your Emirates ID" },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.barcode_scanner),
                        contentDescription = ""
                    )
                })

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = HexToJetpackColor.getColor("E41C38")
                )
            ) {
                Text(text = "Validate", color = Color.White, fontSize = 18.sp)
            }
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
            Text(text = string, color = Color.White)


            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(text = "continue as guest", color = Color.White)

        }

    }
}

class SampleUserProvider : PreviewParameterProvider<HomeResponse.Data.Section> {
    override val values = sequenceOf(HomeResponse.Data.Section())
}

