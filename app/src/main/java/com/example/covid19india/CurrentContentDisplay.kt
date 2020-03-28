package com.example.covid19india

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_current_content_display.*


class CurrentContentDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_content_display)


        var quotes= arrayOf("Stay home if you can and avoid gatherings of more than ten people.","Avoid touching your eyes, nose or mouth with unwashed hands.","Avoid close contact with people who are sick.","Stay home if you are sick, except to get medical care.")

        quote.text=quotes.random()
        var stateSelected=intent.getStringExtra("state")
        state.text=intent.getStringExtra("state")
        cureddata.text=intent.getStringExtra("cured")
        deathsdata.text=intent.getStringExtra("deaths")
        confIdata.text=intent.getStringExtra("indian")
        confFdata.text=intent.getStringExtra("foreign")
        datedata.text=intent.getStringExtra("date")

        individual.setOnClickListener {
            var intent= Intent(this,IndividualDetailsActivity::class.java)
            intent.putExtra("stateSelected",stateSelected)
            startActivity(intent)
        }
    }
}
