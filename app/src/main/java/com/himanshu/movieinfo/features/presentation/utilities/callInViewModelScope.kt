package com.himanshu.movieinfo.features.presentation.utilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <R> ViewModel.callInViewModelScope(block: suspend () -> R) {
	viewModelScope.launch {
		block()
	}
}