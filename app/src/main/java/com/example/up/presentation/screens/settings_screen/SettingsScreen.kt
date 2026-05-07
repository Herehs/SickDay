package com.example.up.presentation.screens.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.note_screen.components.SmallCustomSlider
import com.example.up.presentation.screens.settings_screen.components.ChronicDiseasesComponent
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
//    vm: SettingsViewModel = koinViewModel()
){
//    val settingsState = vm.settingsState.collectAsState()
    val focusManager = LocalFocusManager.current

    val name by remember { mutableStateOf("Jeffrey_Epstein") }
    var email by remember { mutableStateOf("sob***90@gmail.com") }
    var password by remember { mutableStateOf("wwwwwww") }


    var pressureSensitivity by remember { mutableFloatStateOf(0f) }
    var temperatureSensitivity by remember { mutableFloatStateOf(0f) }
    var mSSensitivity by remember { mutableFloatStateOf(0f) }
    var humiditySensitivity by remember { mutableFloatStateOf(0f) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = null
            ) {
                focusManager.clearFocus()
            }

    ){
        Background()
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(57.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffD9D9D9))
                )
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Привет, $name",
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        color = text,
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.8).sp
                    )
                    Text(
                        modifier = Modifier,
                        text = "Дней ведения: 10068",
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        color = textDim,
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.8).sp
                    )
                }
            }

            Section(
                modifier = Modifier.padding(top = 22.dp),
                name = "Безопасность аккаунта"
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = RoundedCornerShape(11.dp)
                        )
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)


                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Сменить почту",
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontFamily = bodyFontFamily,
                            color = text,
                            fontWeight = FontWeight.W400,
                            letterSpacing = -(0.8).sp,

                        )
                        TextField(
                            modifier = Modifier.height(48.dp),
                            value = email,
                            onValueChange = {email = it},
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor= Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                cursorColor = Color(0xff9F8A8F).copy(alpha = .3f),
                                focusedTextColor = textDim,
                                unfocusedTextColor = textDim
                            ),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Right
                            ),
                        )
                    }
                    HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Сменить пароль",
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontFamily = bodyFontFamily,
                            color = text,
                            fontWeight = FontWeight.W400,
                            letterSpacing = -(0.8).sp
                        )
                        TextField(
                            modifier = Modifier.height(48.dp),
                            value = email,
                            onValueChange = {email = it},
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor= Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                cursorColor = Color(0xff9F8A8F).copy(alpha = .3f),
                                focusedTextColor = textDim,
                                unfocusedTextColor = textDim
                            ),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Right
                            ),
                        )
                    }
                }
                ChronicDiseasesComponent(
                    modifier = Modifier.padding(top = 17.dp)
                )
                Section(
                    modifier = Modifier.padding(top = 17.dp),
                    name = "Чувствительность к факторам"
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .shadow(
                                elevation = 1.dp,
                                shape = RoundedCornerShape(11.dp)
                            )
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color.White)
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp)
                    ){
                        Row(
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Давление",
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.8).sp
                            )
                            SmallCustomSlider(
                                modifier = Modifier.size(width = 160.dp, height = 10.dp),
                                value = pressureSensitivity,
                                onValueChange = {pressureSensitivity = it},
                                colors = SliderDefaults.colors(
                                    activeTrackColor = text,
                                    inactiveTrackColor = textDim,
                                    thumbColor = text
                                ),
                                steps = 10,
                                showNumbers = false
                            )
                        }
                        HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                        Row(
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Температура",
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.8).sp
                            )
                            SmallCustomSlider(
                                modifier = Modifier.size(width = 160.dp, height = 10.dp),
                                value = temperatureSensitivity,
                                onValueChange = {temperatureSensitivity = it},
                                colors = SliderDefaults.colors(
                                    activeTrackColor = text,
                                    inactiveTrackColor = textDim,
                                    thumbColor = text
                                ),
                                steps = 10,
                                showNumbers = false
                            )
                        }

                        HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                        Row(
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Магнитные бури",
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.8).sp
                            )
                            SmallCustomSlider(
                                modifier = Modifier.size(width = 160.dp, height = 10.dp),
                                value = mSSensitivity,
                                onValueChange = {mSSensitivity = it},
                                colors = SliderDefaults.colors(
                                    activeTrackColor = text,
                                    inactiveTrackColor = textDim,
                                    thumbColor = text
                                ),
                                steps = 10,
                                showNumbers = false
                            )
                        }

                        HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xffE5E5E5))
                        Row(
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Влажность",
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.8).sp
                            )
                            SmallCustomSlider(
                                modifier = Modifier.size(width = 160.dp, height = 10.dp),
                                value = humiditySensitivity,
                                onValueChange = {humiditySensitivity = it},
                                colors = SliderDefaults.colors(
                                    activeTrackColor = text,
                                    inactiveTrackColor = textDim,
                                    thumbColor = text
                                ),
                                steps = 10,
                                showNumbers = false
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenTest(){
    Scaffold {
        SettingsScreen(modifier = Modifier.padding(it))

    }
}