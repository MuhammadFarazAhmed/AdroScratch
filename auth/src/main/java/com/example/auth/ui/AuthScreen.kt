package com.example.auth.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        val (logo, loginContainer, tvLoginLabel, tvContinueAsGuest, divider) = createRefs()

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

            Text(
                text = "Welcome",
                color = Color.White,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
            Text(
                text = "Abu dhabi visa holders",
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "create account by entering email your emirates ID number",
                color = Color.White,
                fontFamily = Emad,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            TextField(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(4.dp)),
                value = "",
                onValueChange = { it },
                placeholder = { Text("Enter the EMID Number") },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.barcode_scanner),
                        contentDescription = ""
                    )
                })

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = HexToJetpackColor.getColor("e43338")
                )
            ) {
                Text(
                    text = "Validate",
                    color = Color.White,
                    fontFamily = Emad,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        val string = buildAnnotatedString {
            append("Have a account?")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(" Login")
            }
        }

        Text(text = string, color = Color.White, modifier =
        Modifier
            .padding(bottom = 8.dp)
            .constrainAs(tvLoginLabel) {
                centerHorizontallyTo(parent)
                linkTo(start = parent.start, end = parent.end)
                linkTo(top = loginContainer.bottom, bottom = parent.bottom, bias = 0f)
            })

        Divider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(divider) {
                    centerHorizontallyTo(parent)
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = tvLoginLabel.bottom, bottom = tvContinueAsGuest.top)
                })

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("continue as guest")
                }
            },
            color = Color.White,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(tvContinueAsGuest) {
                    centerHorizontallyTo(parent)
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = tvLoginLabel.bottom, bottom = parent.bottom, bias = 0f)
                })

    }
}

class SampleUserProvider : PreviewParameterProvider<HomeResponse.Data.Section> {
    override val values = sequenceOf(HomeResponse.Data.Section())
}

