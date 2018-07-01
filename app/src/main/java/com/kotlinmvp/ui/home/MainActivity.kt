package com.kotlinmvp.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.kotlinmvp.R
import com.kotlinmvp.base.AppController
import com.kotlinmvp.ui.home.roomdb.RoomDb
import com.kotlinmvp.ui.home.roomdb.RoomEntityModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() ,View.OnClickListener {

    private var roomDbInstance:RoomDb?=null
    private var parentView:View?=null
    private var contactList: List<RoomEntityModel>? = ArrayList<RoomEntityModel>();
    private var contactUploadPresenter: ContactUploadPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
        btDeleteAll.setOnClickListener {
            deleteAllFromDb()
        }
    }


    private fun setUi() {
        setInstance()
        setDisplayData()
    }


    private fun setDisplayData() {
        getAllDbData()
    }

    private fun setInstance() {
        roomDbInstance = AppController.getInstance().getRoomDbInstance();
        contactUploadPresenter = ContactUploadPresenter(this)
        btInsert.setOnClickListener(this)
        btUpdate.setOnClickListener(this)
        btDelete.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        val name:String=edName.text.toString()
        val phone:String=edPhone.text.toString()
        if(name.equals(""))
        { showToast("Enter name")
            return
        }
        if(phone.equals("")){
            showToast("Enter phone")
            return
        }

        val model = RoomEntityModel()
        model.userName = name;
        model.phone = phone;

        when (view!!.id) {
            R.id.btInsert -> insertTODb(model)
            R.id.btUpdate -> insertTODb(model)
            R.id.btDelete -> deleteParticularDataFromDb(model)
        }
    }


    private fun insertTODb(model: RoomEntityModel) {
        async(CommonPool){
            roomDbInstance?.getRoomDao()?.insertDataToDb(model)
            getAllDbData()
        }
    }

    private fun updateFromDb(model: RoomEntityModel) {
        async(CommonPool) {
            roomDbInstance?.getRoomDao()?.updateDataToDb(model)
            getAllDbData()
        }
    }


    private fun getAllDbData() {
        launch {
            contactList = roomDbInstance!!.getRoomDao()!!.getAllData();
            notifyData(contactList)
        }

    }


    private fun deleteAllFromDb() {
        async {
            roomDbInstance?.getRoomDao()?.deleteAllToDb();
            getAllDbData()
        }
    }

    private fun deleteParticularDataFromDb(model: RoomEntityModel) {
        async {
            roomDbInstance?.getRoomDao()?.deleteDataToDb(model);
            getAllDbData()
        }
    }


    private fun notifyData(contactList: List<RoomEntityModel>?){
        runOnUiThread {
            if (contactList != null && contactList.size > 0) {
                lin_container.removeAllViews()
                for (aa in contactList) {
                    var contactItem: RoomEntityModel = aa;
                    parentView = LayoutInflater.from(this).inflate(R.layout.item, null);
                    parentView!!.tvName.text = contactItem.userName
                    parentView!!.tvPhone.text = contactItem.phone
                    lin_container.addView(parentView)
                }

            }else{
                lin_container.removeAllViews()
            }
        }
    }


    private fun showToast(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
