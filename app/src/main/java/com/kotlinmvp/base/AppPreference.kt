package com.kotlinmvp.base

import android.content.SharedPreferences

open class AppPreference {
    private var sharedPreference: SharedPreferences?;
    private val DATA: String = "DATA";

    constructor(sharedPreference: SharedPreferences?) {
        this.sharedPreference = sharedPreference
    }

    public fun setData(data: String?) {
        sharedPreference!!.edit().putString(DATA, data).apply()
    }

    public fun getData() = sharedPreference!!.getString(DATA, "");

}