package com.example.covid19india

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class IndividualDetailsAdapter(private val mDataList: ArrayList<IndividualDetailsPOJO>) : RecyclerView.Adapter<IndividualDetailsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = mDataList[position+1].govtId.replace("\"","")
        holder.date.text = mDataList[position+1].dateDiagnosed.replace("\"","")
        holder.loc.text = mDataList[position+1].detectedCity.replace("\"","")+"\n" + mDataList[position].detectedState.replace("\"","")
        holder.status.text = mDataList[position+1].status.replace("\"","")
        var gender = mDataList[position+1].gender.replace("\"","")
        if(gender.equals("Female")) holder.image.setImageResource(R.drawable.female)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.govtid)
        var date: TextView = itemView.findViewById(R.id.diagnosisdate)
        var loc: TextView = itemView.findViewById(R.id.location)
        var status: TextView = itemView.findViewById(R.id.status)
        var image:ImageView = itemView.findViewById(R.id.imageView)
    }
}