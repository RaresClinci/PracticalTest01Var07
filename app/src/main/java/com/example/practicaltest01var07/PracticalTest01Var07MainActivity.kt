package com.example.practicaltest01var07

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    private lateinit var setButton: Button
    private lateinit var randomButton: Button
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText

    private val random = Random()

    private inner class RandomButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            var num = text1.text.toString()

            if (!num.isDigitsOnly() || num.length == 0) {
                text1.setText(random.nextInt(10).toString())
            }

            num = text2.text.toString()
//
            if (!num.isDigitsOnly() || num.length == 0) {
                text2.setText(random.nextInt(10).toString())
            }

            num = text3.text.toString()

            if (!num.isDigitsOnly()|| num.length == 0) {
                text3.setText(random.nextInt(10).toString())
            }

            num = text4.text.toString()

            if (!num.isDigitsOnly()|| num.length == 0) {
                text4.setText(random.nextInt(10).toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_main)

        setButton = findViewById(R.id.set_button)
        randomButton = findViewById(R.id.random_button)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)

        randomButton.setOnClickListener(RandomButtonListener())

    }
}