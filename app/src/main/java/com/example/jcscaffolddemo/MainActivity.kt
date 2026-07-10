package com.example.jcscaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcscaffolddemo.ui.theme.JCScaffoldDemoTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val calculateWindowSize = calculateWindowSizeClass(this)
            MyJCApp(calculateWindowSize)


        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null
            )
        },
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )

    )
}

data class BodyElementData(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)

@Composable
fun AlignYourBody(bodyElementData: BodyElementData, modifier: Modifier = Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Image(
            painter = painterResource(bodyElementData.drawable),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(88.dp)
        )
        Text(
            text = stringResource(bodyElementData.text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewAlignYourBody() {
    AlignYourBody(
        bodyElementData = BodyElementData(
            R.drawable.profile_picture, R.string.align_your_body
        ), modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    JCScaffoldDemoTheme {
        SearchBar()
    }
}

@Composable
fun FavCollectionCard(modifier: Modifier = Modifier, favCollectionData: BodyElementData) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)) {
            Image(
                painter = painterResource(favCollectionData.drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(favCollectionData.text),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }


}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewFavCollectionCard() {

    JCScaffoldDemoTheme() {
        FavCollectionCard(
            modifier = Modifier.padding(8.dp),
            favCollectionData = BodyElementData(R.drawable.profile_picture, R.string.fav_collection)
        )
    }

}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier, bodyElements: List<BodyElementData>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(bodyElements) {
            AlignYourBody(bodyElementData = it, modifier = Modifier)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewAlignYourBodyRow() {
    AlignYourBodyRow(
        modifier = Modifier, bodyElements = listOf(
            BodyElementData(R.drawable.bullbasur, R.string.bullbasur),
            BodyElementData(R.drawable.dragon, R.string.dragon),
            BodyElementData(R.drawable.flier, R.string.flier)
        )
    )
}

@Composable
fun FavCollectionGrid(modifier: Modifier = Modifier, favCollections: List<BodyElementData>) {

    LazyHorizontalGrid(
        modifier = modifier.height(168.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(favCollections) {
            FavCollectionCard(modifier = Modifier.height(80.dp), favCollectionData = it)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFavCollectionGrid() {
    FavCollectionGrid(
        modifier = Modifier, favCollections = listOf(
            BodyElementData(R.drawable.profile_picture, R.string.align_your_body),
            BodyElementData(R.drawable.profile_picture, R.string.align_your_body),
            BodyElementData(R.drawable.profile_picture, R.string.align_your_body),
            BodyElementData(R.drawable.profile_picture, R.string.align_your_body)
        )
    )
}

@Composable
fun HomeScreenPortrait(modifier: Modifier = Modifier) {

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.my_aligned_body) {
            AlignYourBodyRow(
                modifier = Modifier, bodyElements = listOf(
                    BodyElementData(R.drawable.bullbasur, R.string.bullbasur),
                    BodyElementData(R.drawable.dragon, R.string.dragon),
                    BodyElementData(R.drawable.flier, R.string.flier)
                )
            )
        }

        HomeSection(title = R.string.my_fav_collection) {
            FavCollectionGrid(
                modifier = Modifier, favCollections = listOf(
                    BodyElementData(R.drawable.pikachu, R.string.pikachu),
                    BodyElementData(R.drawable.penguin, R.string.penguin),
                    BodyElementData(R.drawable.psyduck, R.string.psyduck),
                    BodyElementData(R.drawable.firefly, R.string.firefly)
                )
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    JCScaffoldDemoTheme() {
        HomeScreenPortrait()
    }
}


@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {

    Column(modifier) {

        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(16.dp)
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeSection() {
    JCScaffoldDemoTheme() {
        HomeSection(R.string.my_aligned_body) {
            AlignYourBodyRow(
                modifier = Modifier, bodyElements = listOf(
                    BodyElementData(R.drawable.profile_picture, R.string.align_your_body),
                    BodyElementData(R.drawable.profile_picture, R.string.align_your_body),
                    BodyElementData(R.drawable.profile_picture, R.string.align_your_body)
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    JCScaffoldDemoTheme() { HomeScreenPortrait() }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun JCAppTheme() {
    JCScaffoldDemoTheme() {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreenPortrait(Modifier.padding(padding))
        }
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        Modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun JCAppLandscape() {
    JCScaffoldDemoTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreenPortrait()
            }
        }
    }
}

@Composable
fun MyJCApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            JCAppTheme()
        }

        WindowWidthSizeClass.Medium -> {
            JCAppLandscape()
        }
    }
}
