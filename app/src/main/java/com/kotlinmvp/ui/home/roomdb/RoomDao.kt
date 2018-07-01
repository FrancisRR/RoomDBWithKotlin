package com.kotlinmvp.ui.home.roomdb

import android.arch.persistence.room.*

@Dao
interface RoomDao {


    @Insert
    fun insertDataToDb(modelObject:RoomEntityModel?)

    @Update
    fun updateDataToDb(modelObject: RoomEntityModel?)

    @Delete
    fun deleteDataToDb(modelObject: RoomEntityModel?)

    @Query("DELETE FROM contact")
    fun deleteAllToDb()

    @Query("SELECT * FROM contact")
    fun getAllData():List<RoomEntityModel>

    @Query("SELECT * FROM contact WHERE number =:phone")
    fun getParticularModel(phone:String?):RoomEntityModel?



}