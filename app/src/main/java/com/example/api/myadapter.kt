package com.example.api

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bscan.R

class MyListAdapter(private val context: Activity, private val title: Array<String>, private val description: Array<String>,private val title1: Array<String>,
                    private val title2: Array<String>,
                    private val title3: Array<String>,private val title4: Array<String>,private val title6: Array<String>,private val title7: Array<String>,
                   private val title9: Array<String>)
    : ArrayAdapter<String>(context, R.layout.activity_customitem,title) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_customitem, null, true)

        val titleText = rowView.findViewById(R.id.textView7) as TextView
        val titletext2 = rowView.findViewById(R.id.textView5) as TextView
        val titleText3 = rowView.findViewById(R.id.textView9) as TextView
        val titletext4= rowView.findViewById(R.id.textView10) as TextView
        val titletext5 = rowView.findViewById(R.id.textView11) as TextView
        val titletext6 = rowView.findViewById(R.id.editTextTextMultiLine) as TextView
        val titletext7 = rowView.findViewById(R.id.editTextTextMultiLine2) as TextView
        val titletext8 = rowView.findViewById(R.id.editTextTextMultiLine3) as TextView
        val titletext10 = rowView.findViewById(R.id.editTextTextMultiLine4) as TextView

        titleText.text = title[position]
        titletext2.text = description[position]
        titleText3.text = title1[position]
        titletext4.text = title2[position]
        titletext5.text = title3[position]
        titletext6.text = title4[position]
        titletext7.text = title7[position]
        titletext8.text = title6[position]
        titletext10.text = title9[position]
        notifyDataSetChanged()

        return rowView
    }
}