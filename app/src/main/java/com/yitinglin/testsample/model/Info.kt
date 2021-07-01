package com.yitinglin.testsample.model

import android.os.Parcelable
import java.io.Serializable


data class Info(
    val description:String="",
    val copyright:String="",
    val title:String="",
    val url:String="",
    val apod_site:String="",
    val date:String="",
    val media_type:String="",
    val hdurl:String=""
) : Serializable







