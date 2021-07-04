package com.ihfazh.yonotifme.feeds.datasources.remote

import com.google.gson.JsonElement
import com.ihfazh.yonotifme.feeds.datasources.remote.data.GoogleSignInBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SembadaService {
    @POST("auth/google")
    fun googleLogin(@Body body: GoogleSignInBody): Call<ResponseBody>
}