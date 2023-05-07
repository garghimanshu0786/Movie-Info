package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.himanshu.movieinfo.R
import com.himanshu.movieinfo.features.presentation.models.APIExceptionEvent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HandleAPIException(apiExceptionEvent: SharedFlow<APIExceptionEvent>) {
	val scaffoldState: ScaffoldState = rememberScaffoldState()
	val context = LocalContext.current
	LaunchedEffect(Unit) {
		apiExceptionEvent.collect { event ->
			when (event) {
				is APIExceptionEvent.Forbidden -> {
					scaffoldState.snackbarHostState.showSnackbar(context.getString(R.string.no_access))
				}

				is APIExceptionEvent.Unauthorized -> {
					scaffoldState.snackbarHostState.showSnackbar(context.getString(R.string.unauthorized))
				}

				is APIExceptionEvent.Unknown -> {
					scaffoldState.snackbarHostState.showSnackbar(event.message)
				}
			}
		}
	}
}
