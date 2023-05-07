package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.himanshu.movieinfo.R

@Composable
fun AddPlaylistPopup(
	showPopup: Boolean,
	addPlaylist: (String) -> Unit,
	closePopup: () -> Unit
) {
	var playlistName by remember(showPopup) { mutableStateOf("") }
	AnimatedVisibility(showPopup) {
		Dialog(
			onDismissRequest = closePopup,
			properties = DialogProperties(
				usePlatformDefaultWidth = false,
			),
		) {
			Column(
				Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp)
					.background(Color.White)
			) {
				Text(
					text = stringResource(id = R.string.enter_a_playlist_name),
					modifier = Modifier.padding(16.dp),
					textAlign = TextAlign.Start,
					fontSize = 26.sp,
					fontWeight = FontWeight.Medium
				)

				OutlinedTextField(
					value = playlistName, onValueChange = {
						playlistName = it
					},
					Modifier
						.padding(horizontal = 16.dp)
						.fillMaxWidth()
				)

				Row(Modifier.padding(vertical = 30.dp)) {
					Spacer(modifier = Modifier.weight(1f))

					Text(
						stringResource(id = R.string.cancel),
						modifier = Modifier.clickable(onClick = closePopup)
					)

					Text(stringResource(id = R.string.ok),
						modifier = Modifier
							.padding(start = 36.dp, end = 16.dp)
							.clickable {
								if (playlistName.isNotBlank()) {
									addPlaylist(playlistName)
									closePopup()
								}
							})
				}
			}
		}
	}
}