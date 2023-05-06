package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieImage(imagePath: String) {
	GlideImage(
		imageModel = { imagePath },
		Modifier
			.fillMaxWidth()
			.height(160.dp),
		imageOptions = ImageOptions(
			contentScale = ContentScale.Crop, alignment = Alignment.Center
		)
	)
}