package com.kotlinmvp.ui.home.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contact")
//data class RoomEntityModel (@PrimaryKey @ColumnInfo(name = "number")var phone:String,@ColumnInfo(name = "name") var userName:String?){
class RoomEntityModel{


    @ColumnInfo(name ="name")
    var userName:String?=""

    @PrimaryKey
    @ColumnInfo(name = "number")
    var phone:String="";


}