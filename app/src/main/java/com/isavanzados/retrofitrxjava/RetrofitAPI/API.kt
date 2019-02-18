package com.isavanzados.retrofitrxjava.RetrofitAPI

import com.isavanzados.retrofitrxjava.Model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface API {

    @GET("photos")
    fun getPosts() :Observable<List<Post>>

}