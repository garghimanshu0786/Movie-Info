package com.himanshu.movieinfo.features.presentation.models

sealed class APIExceptionEvent {
	object Unauthorized : APIExceptionEvent()
	object Forbidden : APIExceptionEvent()
	class Unknown(val message: String) : APIExceptionEvent()
}