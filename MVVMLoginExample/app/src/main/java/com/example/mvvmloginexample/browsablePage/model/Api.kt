package com.example.mvvmloginexample.browsablePage.model

import com.example.mvvmloginexample.signin.model.Login
import retrofit2.Call
import retrofit2.http.*


//("todos/1")
interface Api {

    @get:GET("api/users/2")
    val getTool: Call<Tool>

    @get:GET("api/users/3")
    val getTerm: Call<Term>

    @GET("/api/users")
    fun getPerson(@Query("page") page:String): Call<Person>



    @POST("api/login")
    @FormUrlEncoded
    fun savePost(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<Login>?

    companion object {

        const val BASE_URL = "https://reqres.in/"
    }
}