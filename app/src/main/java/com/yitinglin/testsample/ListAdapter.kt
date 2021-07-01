package com.yitinglin.testsample

import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.yitinglin.testsample.model.Info
import kotlinx.coroutines.*
import java.net.URL
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController

class ListAdapter(private val infoList:List<Info>,private val context:Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemText : TextView = itemView.findViewById(R.id.tvItem)
        val itemImage : ImageView = itemView.findViewById(R.id.ivItem)

        init {
            itemView.setOnClickListener {
                val position : Int =adapterPosition
                var info=Info()
                info=infoList[position]
                val action  = ListFragmentDirections.actionListFragmentToItemFragment(info)
                findNavController(itemView).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cell,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urlImage:URL = URL(infoList[position].url)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }

        GlobalScope.launch(Dispatchers.Main) {
            val bitmap : Bitmap? = result.await()
            bitmap?.apply {
                val savedUri : Uri? = saveToInternalStorage(context)
                holder.itemImage.setImageURI(savedUri)
            }
        }
        holder.itemText.text = infoList[position].title
    }

    override fun getItemCount(): Int {
       return infoList.size
    }

}