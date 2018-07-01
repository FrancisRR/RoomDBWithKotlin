package com.kotlinmvp.base

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kotlinmvp.app.MainThreadBus
import com.kotlinmvp.ui.home.roomdb.RoomDb
import com.squareup.otto.Bus

open class AppController : Application() {


    private val dbName:String="MYDB";
    private var appPreference: AppPreference? = null;
    private var sharedPreferences: SharedPreferences? = null
    private var bus: Bus? = null
    private var gson: Gson? = null;
    private var roomDb:RoomDb?=null

    override fun onCreate() {
        super.onCreate()
        appController=this
        sharedPreferences = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE)
        appPreference = AppPreference(sharedPreferences)
        bus = MainThreadBus()
        gson = Gson();
        roomDb=Room.databaseBuilder(this,RoomDb::class.java,dbName).build();
    }

    companion object {
        private lateinit var appController: AppController;
        fun getInstance(): AppController = appController
    }

    public fun getAppPreference(): AppPreference? = appPreference;
    public fun getBus(): Bus? = bus
    public fun getGson(): Gson? = gson
    public fun getRoomDbInstance()=roomDb


}