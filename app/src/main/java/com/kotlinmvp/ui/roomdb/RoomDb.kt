package com.kotlinmvp.ui.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(RoomEntityModel::class),version = 1)
public abstract class RoomDb :RoomDatabase(){
    public abstract fun getRoomDao():RoomDao?
}