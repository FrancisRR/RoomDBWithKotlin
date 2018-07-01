package com.kotlinmvp.ui.home

import android.content.Context
import com.kotlinmvp.base.AppController
import com.kotlinmvp.ui.home.roomdb.RoomDb
import com.kotlinmvp.ui.home.roomdb.RoomEntityModel
import kotlinx.coroutines.experimental.async

open class ContactUploadPresenter {

    private var context: Context? = null;
    private var roomDb: RoomDb? = null

    constructor(context: Context?) {
        this.context = context
        roomDb = AppController.getInstance().getRoomDbInstance();
    }


    public fun uploadContact(modelObject: RoomEntityModel): List<RoomEntityModel> {
        async {
            roomDb!!.getRoomDao()!!.insertDataToDb(modelObject)
            return@async roomDb!!.getRoomDao()!!.getAllData();
        }
        return emptyList()
    }


    public fun getAllContact(): List<RoomEntityModel> {
        async {
         return@async roomDb?.getRoomDao()?.getAllData();
        }
        return emptyList()
    }


}