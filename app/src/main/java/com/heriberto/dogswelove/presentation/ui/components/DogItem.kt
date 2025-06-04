package com.heriberto.dogswelove.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.heriberto.dogswelove.R
import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.ui.theme.PrimaryText
import com.heriberto.dogswelove.ui.theme.SecondaryText

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    val imageHeight = 200.dp
    val imageWidth = 120.dp
    val cardHeight = 170.dp

    val ageText = pluralStringResource(
        id = R.plurals.dog_age_years,
        count = dog.age,
        dog.age
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(imageHeight)
    ) {
        // First card in back
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .align(Alignment.BottomStart)
                .padding(start = imageWidth / 2),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = imageWidth / 2 + 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            ) {
                Text(
                    text = dog.dogName,
                    style = MaterialTheme.typography.titleMedium,
                    color = PrimaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dog.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SecondaryText,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = ageText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                )
            }
        }

        // Second card with image to front
        Card(
            modifier = Modifier
                .size(width = imageWidth, height = imageHeight)
                .align(Alignment.TopStart),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            AsyncImage(
                model = dog.url,
                contentDescription = dog.dogName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}