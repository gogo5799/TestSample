package com.yitinglin.testsample

import android.graphics.Bitmap
import java.net.URL
import android.graphics.BitmapFactory
import java.io.IOException
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.view.View
import android.widget.ProgressBar
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*
import java.text.SimpleDateFormat

fun URL.toBitmap(): Bitmap?{
    return try {
        BitmapFactory.decodeStream(openStream())
    }catch (e:IOException){
        null
    }
}

fun Bitmap.saveToInternalStorage(context : Context): Uri?{
    val wrapper = ContextWrapper(context)
    var file = wrapper.getDir("images", Context.MODE_PRIVATE)
    file = File(file, "${UUID.randomUUID()}.jpg")
    return try {
        val stream: OutputStream = FileOutputStream(file)
        compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()
        Uri.parse(file.absolutePath)
    } catch (e: IOException){ // catch the exception
        e.printStackTrace()
        null
    }
}

public fun Date.getTimeString(time: String):String{
    return SimpleDateFormat("yyyy MMM dd").format(SimpleDateFormat("yyyy-MM-dd").parse(time))
}

public fun ProgressBar.show(){
    visibility= View.VISIBLE
}
public fun ProgressBar.hide(){
    visibility= View.GONE
}