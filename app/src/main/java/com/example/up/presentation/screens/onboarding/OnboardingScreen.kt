package com.example.up.presentation.screens.onboarding

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.up.R
import com.example.up.presentation.screens.onboarding.components.PageIndicator
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import kotlinx.coroutines.launch

@Composable
fun Onboarding(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
){
    val pageCount = 2
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    var isGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    if(isGranted){
        onButtonClick()
    }
    val locationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        isGranted = fineLocationGranted || coarseLocationGranted
    }

    Box(
        modifier = modifier.fillMaxSize()
    ){
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            when(page){
                0 -> WelcomePage(onButtonClick = { scope.launch { pagerState.animateScrollToPage(1) } })
                1 -> PermissionPage(onButtonClick = {
                    locationLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                })
            }
        }

        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp),
            pageCount = pageCount,
            currentPage = pagerState.currentPage
        )
    }
}

@Composable
fun WelcomePage(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.onboarding_ic),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 70.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .align(alignment = Alignment.Start),
                text = "Добро пожаловать в SickDay",
                fontSize = 36.sp,
                lineHeight = 36.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xffFFFFFF).copy(alpha = .5f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xffEFEFEF),
                        shape = RoundedCornerShape(11.dp)
                    )
                    .clickable(
                        onClick = onButtonClick,
                        indication = null,
                        interactionSource = null
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "Продолжить",
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
            }
        }
    }
}

@Composable
fun PermissionPage(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 70.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        Spacer(Modifier.height(300.dp))
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Start),
            text = "Узнайте погоду в своём городе:",
            fontSize = 24.sp,
            lineHeight = 24.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 70.dp),
            text = "Разрешите доступ к местоположению, чтобы получить прогноз погоды для вашего места",
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400
        )


        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    color = Color(0xffFFFFFF).copy(alpha = .5f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xffEFEFEF),
                    shape = RoundedCornerShape(11.dp)
                )
                .clickable(
                    onClick = onButtonClick,
                    indication = null,
                    interactionSource = null
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier,
                text = "Разрешить местоположение",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = bodyFontFamily,
                color = Color.Black,
                fontWeight = FontWeight.W400
            )
        }
    }
}


@Preview
@Composable
fun OnboardingTest(){
    Onboarding( onButtonClick = {} )
}