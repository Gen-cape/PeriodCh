package com.example.periodch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.periodh.ElementsData

class MainActivity2 : AppCompatActivity() {
    fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { char -> char.isDigit() }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var element = intent.extras?.getString("element")
        var electronNum: Int = 1
        val fullName : TextView = findViewById(R.id.elementDisplay)
        if (isNumeric(element.toString())) {
            if (element != null) {
                electronNum = element.toInt()
                fullName.setText(ElementsData.elements[electronNum - 1])
            }
        }
        else {
            if (element != null || element is String) {
                electronNum = ElementsData.elements.indexOf(element) + 1
                fullName.setText(element.toString())
            }
        }
        val spdf = mapOf("s" to 0, "p" to 1, "d" to 2, "f" to 3)
        val orbitals = arrayOf("1s^", "2s^", "2p^", "3s^", "3p^", "4s^", "3d^", "4p^",
            "5s^", "4d^", "5p^", "6s^", "4f^", "5d^", "6p^", "7s^", "5f^", "6d^", "7p^")
        val exceptions = mapOf(24 to "1s^2 2s^2 2p^6 3s^2 3p^6 4s^1 3d^5" , 29 to "1s^2 2s^2 2p^6 3s^2 3p^6 4s^1 3d^10")
        var configuration = ""
        var orbital = ""
        var e = 0
        var configurationStep = 0
        if (electronNum == 29 || electronNum == 24){
            configuration = exceptions[electronNum].toString()
        }
        else{
            while (true){
                val l = orbitals[configurationStep].get(1).toString()
                val ml = spdf[l]
                val mln = (2* ml!! +1)*2
                if (electronNum > mln){
                    e = mln
                    orbital = orbitals[configurationStep]
                    configuration += orbital + e.toString() + " "
                    configurationStep += 1
                    electronNum -= mln
                }
                else if(electronNum == mln){
                    e = mln
                    orbital = orbitals[configurationStep]
                    configuration += orbital + e.toString() + " "
                    break
                }
                else if (electronNum < mln){
                    e = electronNum
                    orbital = orbitals[configurationStep]
                    configuration += orbital + e.toString() + " "
                    break
                }
            }
        }
        val con : TextView = findViewById(R.id.configDisplay)
        con.setText(configuration)
    }
}