package com.baltajmn.gigi.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Location
import com.baltajmn.gigi.presentation.screens.home.HomeState

@Composable
fun NearContent(
    state: HomeState,
    onFavoriteClicked: (Location) -> Unit
) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(state.coordinatesRestaurant) { location ->
            NearItem(
                modifier = Modifier.fillMaxWidth(),
                location = location,
                isFavorite = state.favoriteRestaurant.map { it.id }
                    .any { it == location.locationId },
                onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}

@Composable
fun NearItem(
    modifier: Modifier = Modifier,
    location: Location,
    isFavorite: Boolean = false,
    onFavoriteClicked: (Location) -> Unit
) {
    Row(
        modifier = modifier
            .width(150.dp)
            .height(60.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.8f)) {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                text = location.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                text = location.distance,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(modifier = Modifier.weight(0.2f)) {
            Icon(
                imageVector = Icons.Filled.Star,
                tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Gray,
                contentDescription = "Favorite",
                modifier = Modifier.clickable {
                    onFavoriteClicked(location)
                }
            )
        }
    }
}

@Preview
@Composable
fun NearItemPreview() {
    NearItem(
        location = Location(
            locationId = 1,
            name = "Pasteleria Juanito",
            distance = "1",
            rating = "1.0",
            bearing = "1",
            addressObj = Address(
                street1 = "Test",
                street2 = "Test",
                city = "Test",
                state = "Test",
                country = "Test",
                postalcode = "Test",
                addressString = "Test",
                phone = "Test",
                latitude = 1.0,
                longitude = 1.0,
                error = null
            )
        )
    ) {}
}