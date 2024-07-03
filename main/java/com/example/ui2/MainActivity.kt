package com.example.ui2

import android.graphics.drawable.Drawable
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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.wear.compose.material.Colors
import com.example.ui2.ui.theme.Ui2Theme
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ui2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Myapp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Myapp(modifier: Modifier = Modifier){
    Column(
         
    ){
        Searchbar(modifier)
        AlignYourBodyRow(modifier)
        FavouriteGrid(modifier)
    }


}
private val alignYourBodyData = listOf(
    R.drawable.fc1_short_mantras to "Good",
    R.drawable.fc2_nature_meditations to "Better",
    R.drawable.fc3_stress_and_anxiety to "Best",
    R.drawable.fc4_self_massage to "The Good",
    R.drawable.fc5_overwhelmed to "The Better",
    R.drawable.fc1_short_mantras to "Good",
    R.drawable.fc2_nature_meditations to "Better"
).map {DrawableStringPair(it.first, it.second)}

private val favouriteData = listOf(
    R.drawable.fc1_short_mantras to "Good",
    R.drawable.fc2_nature_meditations to "Better",
    R.drawable.fc3_stress_and_anxiety to "Best",
    R.drawable.fc4_self_massage to "The Good",
    R.drawable.fc5_overwhelmed to "The Better",
    R.drawable.fc1_short_mantras to "Good",
    R.drawable.fc2_nature_meditations to "Better",
    R.drawable.fc2_nature_meditations to "Better",
).map {DrawableStringPair(it.first, it.second)}

private data class DrawableStringPair(
    @DrawableRes val drawable : Int,
     val text : String
)
@Composable
fun Searchbar(modifier: Modifier = Modifier){
    Surface {
        TextField(
            value = "",
            onValueChange ={},
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null )
            },
            placeholder = {
                //Text(stringResource(id = R.string.placeholder_search))
                Text(text = "search")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = modifier
                .heightIn(min = 64.dp)
                .fillMaxWidth()
        )
    }

}
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
){
    Surface {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = text,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }


}
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
    ){
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 17.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}
@Composable
fun FavouriteGrid(
    modifier: Modifier = Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)

    ) {
        items(favouriteData){
            item -> FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ui2Theme {
       // Searchbar(Modifier.padding(8.dp))
        //AlignYourBodyElement(modifier = Modifier.padding(8.dp))
        //FavoriteCollectionCard(modifier = Modifier.padding(8.dp))
        //AlignYourBodyRow()
        //FavouriteGrid()
        Myapp()
    }
}