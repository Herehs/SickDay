package com.example.up.presentation.common_сomponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.navigation.Routes
import com.example.up.presentation.ui.theme.text


@Composable
fun BottomBar(
    bottomBarItems: List<BottomBarItem>,
    onItemClick: (Routes) -> Unit
){
    var selectedScreen by remember { mutableStateOf(bottomBarItems.first().route) }
    LazyRow(
        Modifier.fillMaxWidth()
            .clip(shape = bottomBarShape())
            .background(Color(0xffFFFFFF).copy(.8f))
            .border(
                width = 1.dp,
                color = Color(0xffCCA7A7),
                shape = bottomBarShape()
            )
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, bottom = 15.dp)
    ){
        items(bottomBarItems){ item ->
            val selected = item.route == selectedScreen

            val color = when(selected){
                true -> { text }
                false -> { Color(0xffB0B0B0) }
            }
            val animatedColor by animateColorAsState(
                targetValue = color
            )

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = {
                            selectedScreen = item.route
                            onItemClick(item.route)
                        }
                    ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    tint = animatedColor
                )
            }
        }
    }
}

data class BottomBarItem(
    val icon: Int,
    val route: Routes
)

fun bottomBarShape() = GenericShape { size, _ ->
    val path = Path().apply {

        moveTo(0f, size.height)
        lineTo(size.width, size.height)
        lineTo(size.width, size.height * .1f)

        quadraticTo(size.width * .99f, size.height * .001f, size.width * .9f, 0f)
        lineTo(size.width * .1f, 0f)
        quadraticTo(size.width * .01f, size.height * .001f,0f, size.height * .1f)

        lineTo(0f, size.height * .1f)
        close()
    }
    addPath(path)
}

@Composable
@Preview
fun BottomBarTest(){
    val list = listOf(
        BottomBarItem(
            icon = R.drawable.drop,
            route = Routes.MainScreen
        ),
        BottomBarItem(
            icon = R.drawable.clock,
            route = Routes.CalendarScreen
        ),
        BottomBarItem(
            icon = R.drawable.drop,
            route = Routes.MainScreen
        ),
        BottomBarItem(
            icon = R.drawable.clock,
            route = Routes.CalendarScreen
        )
    )
    var item: Routes by remember {mutableStateOf(Routes.MainScreen)}
    Scaffold(
        bottomBar = {
            BottomBar(bottomBarItems = list, onItemClick = {item = it})
        }
    ) { a ->
        Background()
        Box(
            modifier = Modifier.fillMaxSize().padding(a),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = when(item){
                    Routes.MainScreen -> {"aaa"}
                    Routes.CalendarScreen -> {"bbb"}
                    else -> {"2"}
                },
                fontSize = 50.sp
            )
        }
    }
}