package com.example.mvvmloginexample.browsablePage.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Tool{
    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }


    class Data {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("first_name")
        @Expose
        var firstName: String? = null

        @SerializedName("last_name")
        @Expose
        var lastName: String? = null

        @SerializedName("avatar")
        @Expose
        var avatar: String? = null

    }
}