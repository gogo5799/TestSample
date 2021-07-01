package com.yitinglin.testsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.yitinglin.testsample.model.InfoRepository
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.yitinglin.testsample.model.Info
import org.json.JSONArray

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}