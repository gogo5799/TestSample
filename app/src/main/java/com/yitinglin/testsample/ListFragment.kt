package com.yitinglin.testsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.yitinglin.testsample.model.Info
import com.yitinglin.testsample.model.InfoRepository
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvList=view.findViewById<RecyclerView>(R.id.rvList)
        val pbList=view.findViewById<ProgressBar>(R.id.pbList)
        pbList.visibility=View.VISIBLE
        rvList.visibility=View.GONE

        val getInfo= ViewModelProvider(this).get(InfoRepository::class.java)
        getInfo.fetchInfo()
        getInfo.info.observe(viewLifecycleOwner, Observer {
            rvList.layoutManager= GridLayoutManager(context,4)
            rvList.adapter=ListAdapter(it,requireContext())
            pbList.visibility=View.GONE
            rvList.visibility=View.VISIBLE
        })

    }

}