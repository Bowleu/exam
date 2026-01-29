package com.bowleu.exam.rx.retrofit

import com.bowleu.exam.rx.models.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("todos/1")
    fun getUser(): Observable<User>
}