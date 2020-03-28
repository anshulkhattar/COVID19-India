package com.example.covid19india

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class IndividualDetailsActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    var individualsData: ArrayList<IndividualDetailsPOJO> = ArrayList()
    var stateList: ArrayList<IndividualDetailsPOJO> = ArrayList()
    lateinit var progressbar:ProgressBar

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_details)

        var selectedState = intent.getStringExtra("stateSelected")
        progressbar = findViewById(R.id.progressBar)

        var govtId: String = ""
        var dateDiagnosed: String = ""
        var gender: String = ""
        var detectedCity: String = ""
        var detectedState: String = ""
        var status: String = ""
        val inputStream: InputStream = resources.openRawResource(R.raw.individualdetails)
        val reader = BufferedReader(InputStreamReader(inputStream))

        for(line in reader.lines()){
            var tokens=line.split(",")
            if(tokens.size>10 ){
                govtId=tokens[2]
                dateDiagnosed=tokens[3]
                gender=tokens[5]
                detectedCity=tokens[8]
                detectedState=tokens[9]
                status=tokens[11]

                val individualData=IndividualDetailsPOJO(govtId,dateDiagnosed, gender, detectedCity, detectedState, status)
                individualsData.add(individualData)
            }
            Log.d("token", individualsData.toString())

            for(obj in individualsData){
                if(obj.detectedState.replace("\"","").equals(selectedState)){
                    stateList.add(obj)
                }
            }


        }

        mRecyclerView = findViewById(R.id.recyclerview)
        var mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = IndividualDetailsAdapter(stateList)
        mRecyclerView!!.adapter = mAdapter

    }
}