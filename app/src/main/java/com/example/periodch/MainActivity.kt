package com.example.periodch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.periodh.ElementsData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        val input : AutoCompleteTextView = findViewById(R.id.input)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ElementsData.elements)
        input.setAdapter(adapter)
        input.threshold = 1
//        input.onItemClickListener = AdapterView.OnItemClickListener{ parent, _, position, id ->
//            selected = parent.getItemAtPosition(position).toString()
//        }
        button.setOnClickListener {
            var intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra("element", input.text.toString())
            startActivity(intent)
        }

    }
}