package com.example.covid19india

import Base
import Statewise
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    var stateList:List<Statewise>? = null
    var stateSelected:String=""
    lateinit var search: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner= this.findViewById(R.id.spinner)

        search=findViewById(R.id.button)

        loadStateData()

        search.setOnClickListener {
            for (i in 0..stateList!!.size - 1) {
                if (stateList!![i].state.equals(stateSelected)) {
                    Log.d("hy",stateList!![i].toString())
                    var intent = Intent(this, CurrentContentDisplay::class.java)
                    intent.putExtra("conf", ""+stateList!![i].confirmed)
                    intent.putExtra("rev", ""+stateList!![i].recovered)
                    intent.putExtra("act", ""+stateList!![i].active)
                    intent.putExtra("death", ""+stateList!![i].deaths)
                    intent.putExtra("state", ""+stateList!![i].state)
                    startActivity(intent)
                }
            }
        }



        var stateNames=arrayOf("Choose your location","Kerala","Haryana","Lakshadweep","Daman and Diu","Dadra and Nagar Haveli","Sikkim","Nagaland","Jharkhand","Arunachal Pradesh","Tripura","Meghalaya",
        "Assam","Mizoram","Manipur","Puducherry","Andaman and Nicobar Islands","Goa","Odisha","Himachal Pradesh","Uttarakhand","Chhattisgarh","Chandigarh","Bihar","West Bengal","Andhra Pradesh","Ladakh",
        "Jammu and Kashmir","Madhya Pradesh","Tamil Nadu","Punjab","Delhi","Gujarat","Rajasthan","Uttar Pradesh","Telangana","Karnataka","Maharashtra")

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinner_item, stateNames)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?, position: Int, id: Long) {
                    stateSelected=parent?.getItemAtPosition(position).toString()
                    Log.d("selected",stateSelected)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // write code to perform some action
                }
            }
        }
    }
        fun loadStateData(){
        var mApiService = RetrofitClient.client.create(
            ApiInterface::class.java)
        val call = mApiService!!.getStateData()
        call.enqueue(object : Callback<Base> {

            override fun onResponse(call: Call<Base>, response: Response<Base>)
            {
                stateList=response.body()!!.data.statewise
                Log.d("valll", stateList!!.toString())
            }


            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.e(ContentValues.TAG, "Got error : " + t.localizedMessage)
            }
        })
    }
}


