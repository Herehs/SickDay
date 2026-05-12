package com.example.up.presentation.screens.registration_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.Background3
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier
){
    Background3(
        primaryColor = Color(0xffB2A1FF).copy(alpha = .8f),
        secondaryColor = Color(0xffF7A1FF).copy(alpha = .4f)
    )

    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 50.dp)
                .align(alignment = Alignment.Start),
            text = "Регистрация",
            fontSize = 40.sp,
            lineHeight = 50.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400,
            letterSpacing = -(0.8).sp
        )

        Text(
            modifier = Modifier
                .padding(top = 170.dp)
                .align(alignment = Alignment.Start),
            text = "Логин",
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontFamily = bodyFontFamily,
            color = Color.Black,
            fontWeight = FontWeight.W400,
            letterSpacing = -(0.8).sp
        )
        BasicTextField(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 14.dp)
                .height(40.dp)
                .width(330.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(9.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp) // опционально
                ),
            value = login,
            onValueChange = {login = it},
            textStyle = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 30.dp)
                .align(alignment = Alignment.Start),
            text = "Пароль",
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontFamily = bodyFontFamily,
            color = Color.Black,
            fontWeight = FontWeight.W400,
            letterSpacing = -(0.8).sp
        )
        BasicTextField(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 14.dp)
                .height(40.dp)
                .width(330.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(9.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp) // опционально
                ),
            value = password,
            onValueChange = {password = it},
            textStyle = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 30.dp)
                .height(40.dp)
                .width(330.dp)
                .background(
                    color = Color(0xffD8DEFF),
                    shape = RoundedCornerShape(8.dp) // опционально
                )
                .border(
                    width = 1.dp,
                    color = Color(0xff97AAFE),
                    shape = RoundedCornerShape(9.dp)
                )
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = null
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier,
                text = "Создать аккаунт",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = bodyFontFamily,
                color = Color.Black,
                fontWeight = FontWeight.W400,
                letterSpacing = -(0.8).sp
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 280.dp)
                .align(alignment = Alignment.CenterHorizontally),
            text = "Уже есть аккаунт? Войти",
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontFamily = bodyFontFamily,
            color = Color(0xff4455A7),
            fontWeight = FontWeight.W400,
            textDecoration = TextDecoration.Underline,
            letterSpacing = -(0.8).sp
        )
    }
}

@Preview
@Composable
fun RegistrationScreenTest(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ){ paddingValues ->
        RegistrationScreen(Modifier.padding(paddingValues))
    }

}