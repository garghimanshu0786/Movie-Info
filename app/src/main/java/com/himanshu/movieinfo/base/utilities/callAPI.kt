package com.himanshu.movieinfo.base.utilities

import com.himanshu.movieinfo.features.domain.entity.ResultEntity
import retrofit2.Response

suspend fun <D, C> callAPI(
	apiCall: suspend () -> Response<D>,
	parse: (D?) -> C,
) = try {
	with(apiCall()) {
		if (isSuccessful) {
			ResultEntity.Success(parse(body()))
		} else {
			ResultEntity.Failure.APIErrorFailure(code(), message(), errorBody().toString())
		}
	}
} catch (ex: Exception) {
	ex.printStackTrace()
	ResultEntity.Failure.ErrorException(ex)
}