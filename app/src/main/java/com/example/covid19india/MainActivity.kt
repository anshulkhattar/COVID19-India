package com.example.covid19india

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sno:String=""
        var date:String=""
        var state:String=""
        var confIndianNationals:String=""
        var confForeignNationals:String=""
        var cured:String=""
        var deaths:String=""

        spinner= this.findViewById(R.id.spinner)
        var arr= ArrayList<Covid19POJO>()




        val inputStream: InputStream = resources.openRawResource(R.raw.covidindia)
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
        for(line in reader.lines()){
            val tokens=line.split(',')
            sno=tokens[0]
            date=tokens[1]
            state=tokens[3]
            confIndianNationals=tokens[4]
            confForeignNationals=tokens[5]
            cured=tokens[6]
            deaths=tokens[7]

            val currentData=Covid19POJO(sno,date,state,confIndianNationals,confForeignNationals,cured, deaths)

            arr.add(currentData)
        }

        var stateNames=arrayOf("Choose your location","Kerala","Haryana","Lakshadweep","Daman and Diu","Dadra and Nagar Haveli","Sikkim","Nagaland","Jharkhand","Arunachal Pradesh","Tripura","Meghalaya",
        "Assam","Mizoram","Manipur","Puducherry","Andaman and Nicobar Islands","Goa","Odisha","Himachal Pradesh","Uttarakhand","Chhattisgarh","Chandigarh","Bihar","West Bengal","Andhra Pradesh","Ladakh",
        "Jammu and Kashmir","Madhya Pradesh","Tamil Nadu","Punjab","Delhi","Gujarat","Rajasthan","Uttar Pradesh","Telangana","Karnataka","Maharashtra")
        var stateSelected:String=""
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

        var stateList=ArrayList<Covid19POJO>()
        button.setOnClickListener {

            for(obj in arr){
                if(obj.state.contentEquals(stateSelected)){
                    stateList.add(obj)
                }
            }
            if(stateList.size==0)   Toast.makeText(this,"No data available",Toast.LENGTH_SHORT).show()
            else {
                var intent = Intent(this, CurrentContentDisplay::class.java)
                intent.putExtra("date", stateList[stateList.size - 1].date)
                intent.putExtra("state", stateList[stateList.size - 1].state)
                intent.putExtra("indian", stateList[stateList.size - 1].confIndian)
                intent.putExtra("foreign", stateList[stateList.size - 1].confForeign)
                intent.putExtra("cured", stateList[stateList.size - 1].cured)
                intent.putExtra("deaths", stateList[stateList.size - 1].deaths)
                startActivity(intent)
            }
            //Log.d("data",stateList[stateList.size-1].date)
        }
    }
}


