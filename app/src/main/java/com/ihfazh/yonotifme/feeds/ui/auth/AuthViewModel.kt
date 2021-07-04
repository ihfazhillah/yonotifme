package com.ihfazh.yonotifme.feeds.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.ihfazh.yonotifme.feeds.datasources.remote.SembadaService
import com.ihfazh.yonotifme.feeds.datasources.remote.data.GoogleSignInBody
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.usecases.DetailFeedUseCase
import com.ihfazh.yonotifme.feeds.usecases.ListFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Callback
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sembadaService: SembadaService
): ViewModel(), retrofit2.Callback<ResponseBody> {
    fun login(accessToken: String, code: String){
        sembadaService.googleLogin(
            GoogleSignInBody(accessToken, "")
        ).enqueue(this)
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        Log.d(TAG, "response body: ${response.body()}")
        Log.d(TAG, "onResponse: ${response.raw()}")
        Log.d(TAG, "onResponse: ${response.body()?.string()}")
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        Log.d(TAG, "error body: ${t.message}")
    }

    private val TAG = "AuthViewModel"
}