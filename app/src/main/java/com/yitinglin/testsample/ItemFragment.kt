package com.yitinglin.testsample

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.*
import java.net.URL
import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import com.yitinglin.testsample.model.Info
import java.util.*

class ItemFragment : Fragment() {

    private val args  by navArgs<ItemFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemLayout = view.findViewById<LinearLayout>(R.id.itemLayout)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val tvTitle  = view.findViewById<TextView>(R.id.tvTitle)
        val tvCopyright = view.findViewById<TextView>(R.id.tvCopyright)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val pbItem = view.findViewById<ProgressBar>(R.id.pbItem)
        val date:Date=Date()

        val getInfo : Info = args.currentInfo
        val urlImage: URL = URL(getInfo.hdurl)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }
        pbItem.visibility=View.VISIBLE
        itemLayout.visibility=View.GONE

        GlobalScope.launch(Dispatchers.Main) {
            val bitmap : Bitmap? = result.await()
            bitmap?.apply {
                val savedUri : Uri? = saveToInternalStorage(view.context)
                if (savedUri!=null){
                    itemLayout.background=Drawable.createFromPath(savedUri.toString())
                }

                tvDate.text= date.getTimeString(getInfo.date)
                tvTitle.text= getInfo.title
                tvCopyright.text= getInfo.copyright
                tvDescription.text= getInfo.description
                pbItem.visibility=View.GONE
                itemLayout.visibility=View.VISIBLE
            }
        }

    }
}