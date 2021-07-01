package com.yitinglin.testsample.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.json.JSONArray
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.yitinglin.testsample.VolleySingleton
import org.json.JSONException

class InfoRepository (application: Application): AndroidViewModel(application){
    private val _info : MutableLiveData<List<Info>> by lazy {
        MutableLiveData<List<Info>>()
    }
    public val info : LiveData<List<Info>> = _info
    public fun fetchInfo(){
        val jsonArray = object :  JsonArrayRequest(Request.Method.GET,"https://raw.githubusercontent.com/cmmobile/NasaDataSet/main/apod.json",null,
                Response.Listener {
                    try {
                        Log.i(" Response=>",it.toString())
                        val  getListInfo = mutableListOf<Info>()
                        for (i in 0..it.length()){
                            val jsonInfo= Gson().fromJson(it[i].toString(),Info::class.java)
                            getListInfo.add(jsonInfo)
                            if (it.length()==getListInfo.size){
                                _info.value= getListInfo
                            }
                        }
                    }catch (e: JSONException){
                        Log.i(" JSONException ", "EXC: $it")
                    }
                },Response.ErrorListener {
        }){}
        VolleySingleton.getInstance(getApplication()).requestQueue.add(jsonArray)
    }
}