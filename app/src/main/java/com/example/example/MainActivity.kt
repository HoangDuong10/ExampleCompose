package com.example.example

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.example.model.Info
import com.example.example.model.TimeLineUser
import com.example.example.ui.theme.ExampleTheme
import java.time.LocalTime
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainActivityScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainActivityScreen() {
    Column(Modifier.background(Color.White)) {
        Header()
        Category(getList())
        ListTag(listTag = getListTag())
        TabLayout()
    }
}

@Composable
fun Header() {
    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
    ) {
        val (imgBack, imgAvata, tvUserName, tvPhoneAndAddress, tvGenderAndAge) = createRefs()
        val greenColor = colorResource(id = R.color.green)
        Icon(
            painterResource(id = R.drawable.ic_back),
            contentDescription = "icon back",
            tint = greenColor,
            modifier =
                Modifier
                    .size(50.dp)
                    .constrainAs(imgBack) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(imgAvata.bottom)
                    },
        )

        Image(
            imageVector = Icons.Outlined.Person,
            contentDescription = "image avata",
            modifier =
                Modifier
                    .size(100.dp)
                    .border(BorderStroke(1.dp, Color.Gray), shape = CircleShape)
                    .constrainAs(imgAvata) {
                        start.linkTo(imgBack.end)
                        top.linkTo(parent.top)
                    },
        )

        val sizeIcon = dimensionResource(id = R.dimen.define_dimen_24)

        Row(
            modifier =
                Modifier
                    .padding(start = 12.dp)
                    .constrainAs(tvUserName) {
                        start.linkTo(imgAvata.end)
                        top.linkTo(parent.top)
                    },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.user_name),
                fontSize = getNormalTextSize(),
                fontWeight = FontWeight.Bold,
                fontFamily = getFontFamily(),
                modifier = Modifier.padding(2.dp),
            )
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = greenColor,
                modifier = Modifier.size(sizeIcon),
            )
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone",
                tint = greenColor,
                modifier = Modifier.size(sizeIcon),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flag),
                contentDescription = "Flag",
                tint = greenColor,
                modifier = Modifier.size(sizeIcon),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = "circle",
                tint = greenColor,
                modifier = Modifier.size(sizeIcon),
            )
        }

        Text(
            text = stringResource(id = R.string.phone_and_address),
            fontSize = getNormalTextSize(),
            fontFamily = getFontFamily(),
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier =
                Modifier
                    .padding(start = 10.dp)
                    .padding(top = 10.dp)
                    .constrainAs(tvPhoneAndAddress) {
                        start.linkTo(imgAvata.end)
                        top.linkTo(tvUserName.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
        )

        Text(
            text = stringResource(id = R.string.gender_and_age),
            fontSize = getNormalTextSize(),
            fontFamily = getFontFamily(),
            color = Color.Gray,
            modifier =
                Modifier
                    .padding(start = 10.dp)
                    .padding(top = 10.dp)
                    .constrainAs(tvGenderAndAge) {
                        start.linkTo(imgAvata.end)
                        top.linkTo(tvPhoneAndAddress.bottom)
                    },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListTag(listTag: List<String>) {
    FlowRow(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        listTag.forEach { tag ->
            ItemTag(tag = tag)
        }
    }
}

@Composable
fun Category(listInfo: List<Info>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        columns = GridCells.Fixed(3),
        content = {
            items(listInfo) { category ->
                ItemCategory(info = category)
            }
        },
    )
}



@Composable
fun ItemCategory(info: Info) {
    Column(
        modifier =
            Modifier
                .border(
                    BorderStroke(2.dp, colorResource(id = R.color.bg_grey)),
                    shape = RoundedCornerShape(10.dp),
                ).background(colorResource(id = R.color.bg_grey), shape = RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = info.title,
            fontSize = getNormalTextSize(),
            fontFamily = getFontFamily(),
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = info.value,
                fontSize = getNormalTextSize(),
                fontFamily = getFontFamily(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
            )
            if (!info.isIncrease) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_increase),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .size(20.dp)
                            .padding(bottom = 8.dp),
                    tint = colorResource(id = R.color.green),
                )
                Text(
                    text = info.value,
                    fontFamily = getFontFamily(),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.green),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
        }
    }
}

@Composable
fun ItemTag(tag: String) {
    Text(
        text = tag,
        fontWeight = FontWeight.W500,
        fontSize = getNormalTextSize(),
        fontFamily = getFontFamily(),
        modifier =
            Modifier
                .background(colorResource(id = R.color.bg_grey))
                .padding(2.dp),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExampleTheme {
        MainActivityScreen()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TabLayout() {
    val tabItems = listOf("Hành trình", "Đơn hàng", "Hành động")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItems.size}
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    Column {
        Box(
            modifier =
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(id = R.color.bg_grey)),
        )
        TabRow(
            divider = {},
            selectedTabIndex = pagerState.currentPage,
            modifier =
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth(),
            containerColor = colorResource(id = R.color.bg_tablayout),
            indicator = {
                // Box trống để loại bỏ gạch dưới
                Box {}
            },
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                val selected = pagerState.currentPage == index
                val tvQuantity = if (index == 1) 5 else 0
                Tab(
                    modifier =
                        Modifier
                            .padding(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(if (selected) Color.White else Color.Transparent)
                            .wrapContentWidth(),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = tabItem,
                                color = if (selected) Color.Black else Color.Gray,
                                fontSize = getNormalTextSize(),
                            )
                            if (tvQuantity > 1) {
                                Box(
                                    modifier =
                                        Modifier
                                            .padding(start = 2.dp)
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(Color.Red),
                                ) {
                                    Text(
                                        text = tvQuantity.toString(),
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        modifier = Modifier.align(Alignment.Center),
                                    )
                                }
                            }
                        }
                    },
                )
            }
        }
        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            when (page) {
                0 -> TabContent(tabName = "Tab 1 Content")
                1 -> TabContent(tabName = "Tab 2 Content")
                2 -> ListTimeLine()
            }
        }
    }
}

@Composable
fun TabContent(tabName: String) {
    Text(
        text = tabName,
        fontSize = getNormalTextSize(),
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxSize(),
        textAlign = TextAlign.Center,
    )
}

enum class DividerOrientation {
    Horizontal,
    Vertical,
}

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    orientation: DividerOrientation = DividerOrientation.Horizontal,
    dashGap: Dp = 5.dp,
    dashLength: Dp = 5.dp,
    dashThickness: Dp = 3.dp,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = dashThickness.toPx()
            val gap = dashGap.toPx()
            val length = dashLength.toPx()

            val paint =
                Paint().apply {
                    this.color = color
                    style = androidx.compose.ui.graphics.PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(length, gap), 0f)
                }
            if (orientation == DividerOrientation.Horizontal) {
                val centerY = size.height / 2
                drawLine(
                    color = color,
                    start = Offset(0f, centerY),
                    end = Offset(size.width, centerY),
                    strokeWidth = strokeWidth,
                    pathEffect = paint.pathEffect,
                )
            } else {
                val centerX = size.width / 2
                drawLine(
                    color = color,
                    start = Offset(centerX, 0f),
                    end = Offset(centerX, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = paint.pathEffect,
                )
            }
        }
    }
}

@Composable
fun ItemTimeLine(timeLineUser: TimeLineUser) {
    ConstraintLayout {
        val (ivCircle, tvTitle, tvTime, divider) = createRefs()
        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            tint = colorResource(id = R.color.green),
            modifier =
                Modifier
                    .padding(top = 10.dp)
                    .size(10.dp)
                    .constrainAs(ivCircle) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
        )
        Text(
            text =
                buildAnnotatedString {
                    withStyle(
                        style =
                            SpanStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                    ) {
                        append("Dương")
                    }
                    withStyle(style = SpanStyle(colorResource(id = R.color.black))) {
                        append(timeLineUser.title)
                    }
                },
            modifier =
                Modifier.padding(4.dp).constrainAs(tvTitle) {
                    start.linkTo(ivCircle.end)
                    top.linkTo(parent.top)
                },
        )
        Text(
            text = timeLineUser.date.toString(),
            fontSize = 16.sp,
            modifier =
                Modifier.padding(4.dp).constrainAs(tvTime) {
                    top.linkTo(tvTitle.bottom)
                    start.linkTo(ivCircle.end)
                },
        )

        CustomDivider(
            color = colorResource(id = R.color.green),
            orientation = DividerOrientation.Vertical,
            dashGap = 2.dp,
            dashLength = 6.dp,
            dashThickness = 1.dp,
            modifier =
                Modifier
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        top.linkTo(ivCircle.bottom)
                        bottom.linkTo(tvTime.bottom)
                        height = Dimension.fillToConstraints
                    }.width(10.dp),
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ListTimeLine() {
    val listTimeLine = getListTimeLine()
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(listTimeLine) { item ->
            ItemTimeLine(timeLineUser = item)
        }
    }
}

@Composable
fun getNormalTextSize(): TextUnit = dimensionResource(id = R.dimen.text_size_normal).value.sp

@Composable
fun getFontFamily(): FontFamily =
    FontFamily(
        Font(resId = R.font.tahoma, weight = FontWeight.Normal),
    )

fun getListTag(): List<String> =
    listOf(
        "#Thích đổi trả",
        "#Thích FreeShip",
        "#Thích đồ lụa",
        "#Thích khuyến mãi",
        "#Thích thử hàng",
        "#Thích bảo hành",
        "#Thích đủ thứ",
    )

@RequiresApi(Build.VERSION_CODES.O)
fun getRandomTime(): LocalTime {
    val randomHour = Random.nextInt(0, 24)
    val randomMinute = Random.nextInt(0, 60)
    return LocalTime.of(randomHour, randomMinute)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getListTimeLine(): List<TimeLineUser> {
    val list: MutableList<TimeLineUser> = mutableListOf()
    list.add(
        TimeLineUser(
            " đã đặt một sản peeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeehẩm",
            getRandomTime(),
        ),
    )
    list.add(TimeLineUser(" đã đặt một sảuuuuuuuuuuuuuun phẩm1", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm2", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm3", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm4", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm5", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm6", getRandomTime()))
    list.add(TimeLineUser(" đã đặt một sản phẩm7", getRandomTime()))
    return list
}

private fun getList(): List<Info> {
    val list: MutableList<Info> = mutableListOf()
    list.add(Info("Chi tiêu TB", "3,3 Tr", false))
    list.add(Info("Tỉ lệ chốt", "30%", false))
    list.add(Info("Tốc độ chốt", "4 bánh"))
    list.add(Info("SP Mẹ & bé", "20 ĐH"))
    list.add(Info("Chu kì mua", "19 ngày"))
    list.add(Info("Tỷ lệ matching", "90%"))
    return list
}