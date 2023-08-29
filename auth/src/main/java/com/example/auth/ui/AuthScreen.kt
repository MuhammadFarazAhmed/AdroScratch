package com.example.auth.ui

import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.HexToJetpackColor
import com.example.adro.theme.Emad
import com.example.auth.R
import com.example.auth.vm.AuthViewModel
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthScreen(
    onBackClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    vm: AuthViewModel = getViewModel()
) {
    BackHandler {
        onBackClick()
    }

    val isLogin = vm.isLogin.collectAsStateLifecycleAware()

    if (isLogin.value)
        onLoginSuccess()

    Surface(modifier = Modifier.background(Color.White)) {

        Image(
            painter = painterResource(id = R.drawable.adro_image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            AppLogo()
            LoginScreen(vm)
        }

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            AppLogo()
            LoginScreen(getViewModel())
        }

    }
}

@Composable
fun AppLogo() {
    Row {
        Image(
            painter = painterResource(id = com.example.base.R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun EMIDScreen() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (loginContainer, tvLoginLabel, tvContinueAsGuest, divider) = createRefs()


        Column(modifier = Modifier.constrainAs(loginContainer) {
            centerHorizontallyTo(parent)
            linkTo(top = parent.top, bottom = parent.bottom, bias = 0.6f)
        }) {

            Text(
                text = "Welcome",
                color = Color.White,
                modifier = Modifier.wrapContentSize(Center)
            )
            Text(
                text = "Abu dhabi visa holders",
                color = Color.White
            )
            Text(
                text = "create account by entering email your emirates ID number",
                color = Color.White,
                fontFamily = Emad,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextField(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(4.dp)),
                value = "",
                onValueChange = { },
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
                    containerColor = HexToJetpackColor.getColor("e43338")
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

@Composable
fun LoginScreen(vm: AuthViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (tvDescription, etMobile, bRegister, etPassword, bSignIn, tvForgotPassword, etCountryCode) = createRefs()

        val mobile = remember {
            mutableStateOf(
                TextFieldValue("+971555555555")
            )
        }

        val password = remember {
            mutableStateOf(
                TextFieldValue("Faraz@#1234")
            )
        }

        Text(
            text = stringResource(R.string.welcome_to_where_you_belong),
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvDescription) {
                    linkTo(top = parent.top, bottom = parent.bottom, bias = 0.4f)
                }
                .padding(vertical = 12.dp)
                .wrapContentSize(Center),
            fontFamily = Emad,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .clickable { }
                .padding(end = 4.dp)
                .fillMaxWidth(0.3f)
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .constrainAs(etCountryCode) {
                    linkTo(top = etMobile.top, bottom = etMobile.bottom)
                    linkTo(parent.start, etMobile.start)
                    height = fillToConstraints
                },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.icons_united_arab_emirates),
                contentDescription = "",
                modifier = Modifier.wrapContentSize()
            )
            Text(
                text = "+971",
                color = Color.Black,
                fontFamily = Emad,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

        }

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(start = 4.dp)
                .constrainAs(etMobile) {
                    linkTo(top = tvDescription.bottom, bottom = parent.bottom, bias = 0.0f)
                    linkTo(parent.start, parent.end, bias = 1.0f)
                }
                .wrapContentSize()
                .fillMaxWidth(0.7f)
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = mobile.value,
            onValueChange = { mobile.value = it },
            placeholder = { Text("e.g 5465647") })

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .constrainAs(etPassword) {
                    linkTo(top = etMobile.bottom, bottom = parent.bottom, bias = 0.0f)
                }
                .padding(vertical = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text("Password") })

        Button(
            enabled = validateMobile(mobile),
            onClick = {
                vm.login(mobile.value.text, password.value.text)
            },
            modifier = Modifier
                .constrainAs(bSignIn) {
                    linkTo(top = etPassword.bottom, bottom = parent.bottom, bias = 0.0f)
                }
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = HexToJetpackColor.getColor("e43338")
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = "Sign in",
                color = Color.White,
                fontFamily = Emad,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .constrainAs(tvForgotPassword) {
                    centerHorizontallyTo(parent)
                    linkTo(parent.start, parent.end)
                    linkTo(top = bSignIn.bottom, bottom = bRegister.top)
                },
            text = "Forgot Password?",
            color = Color.White,
            fontFamily = Emad,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .constrainAs(bRegister) {
                    linkTo(top = tvForgotPassword.bottom, bottom = parent.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = HexToJetpackColor.getColor("ffffff")
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = "Register",
                color = Color.Black,
                fontFamily = Emad,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun validateMobile(mobile: MutableState<TextFieldValue>) =
    Patterns.PHONE.matcher(mobile.value.text).matches()

@Composable
fun RegisterScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp),
    ) {

        Text(
            text = "Ready to start saving?",
            color = Color.White,
            modifier = Modifier
                .wrapContentSize(Center),
            fontFamily = Emad,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Register and try the app today!",
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 38.dp)
                .wrapContentSize(Center),
            fontFamily = Emad,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("First Name") })

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("Last Name") })

        Row(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .clickable(false) {

                    }
                    .padding(end = 4.dp)
                    .fillMaxWidth(0.3f)
                    .height(55.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icons_united_arab_emirates),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 12.dp)
                )
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = "+971",
                    color = Color.Black,
                    fontFamily = Emad,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            TextField(
                colors = TextFieldDefaults.colors(
                    contentColorFor(Color.LightGray), Color.LightGray,
                ),
                modifier = Modifier
                    .clickable(false) {

                    }
                    .padding(bottom = 12.dp)
                    .wrapContentSize()
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(4.dp)),
                value = "",
                onValueChange = { },
                placeholder = { Text("e.g 5465647") })
        }

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("Golden Visa Category") })

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("Email") })

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("Password") })

        TextField(
            colors = TextFieldDefaults.colors(
                contentColorFor(MaterialTheme.colorScheme.surface.copy(alpha = 1.0f)),
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentSize()
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp)),
            value = "",
            onValueChange = { },
            placeholder = { Text("Confirm Password") })


        CustomCheckBox(
            createMultiStyleString(
                stringResource(R.string.i_agree_to_the_terms_of_use).substringBefore("-"),
                stringResource(R.string.i_agree_to_the_terms_of_use).substringAfterLast("-")
            )
        )

        CustomCheckBox(
            createMultiStyleString(
                stringResource(R.string.i_agree_to_the_end_user_licence_agreement).substringBefore("-"),
                stringResource(R.string.i_agree_to_the_end_user_licence_agreement).substringAfterLast(
                    "-"
                )
            )
        )
        CustomCheckBox(
            createMultiStyleString(
                stringResource(R.string.keep_me_updated_with_all_exlcusive_offer).substringBefore("-"),
                stringResource(R.string.keep_me_updated_with_all_exlcusive_offer).substringAfterLast(
                    "-"
                )
            )
        )

        Button(
            onClick = { },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = HexToJetpackColor.getColor("e43338")
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = "Submit",
                color = Color.White,
                fontFamily = Emad,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            text = buildAnnotatedString {
                append("Already have a account? ")
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Sign in")
                }
            },
            color = Color.White,
            fontFamily = Emad,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

fun createMultiStyleString(simple: String, spanned: String) = buildAnnotatedString {
    append(simple.substringBefore("-"))
    withStyle(
        SpanStyle(
            fontFamily = Emad,
            fontWeight = FontWeight.Bold
        )
    ) {
        append(spanned.substringAfterLast("-"))
    }
}

@Composable
fun CustomCheckBox(text: AnnotatedString) {
    val isCheck = remember { mutableStateOf(false) }

    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Card(
            modifier = Modifier.background(
                Color.White,
                RoundedCornerShape(4.dp)
            ),
            elevation = CardDefaults.cardElevation(),
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        Color.White,
                        RoundedCornerShape(4.dp)
                    )
                    .size(25.dp)
                    .clickable {
                        isCheck.value = !isCheck.value
                    },
                contentAlignment = Center
            ) {
                if (isCheck.value)
                    Image(
                        painterResource(id = R.drawable.black_tick),
                        contentDescription = "",
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
            }
        }

        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 10.dp),
            text = text,
            fontSize = 14.sp,
            color = Color.White
        )
    }

}

class SampleUserProvider : PreviewParameterProvider<HomeResponse.Data.Section> {
    override val values = sequenceOf(HomeResponse.Data.Section())
}

