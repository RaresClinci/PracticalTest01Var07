package com.example.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {
    private lateinit var sumButton: Button
    private lateinit var prodButton: Button
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText

    private var num1 = 0
    private var num2 = 0
    private var num3 = 0
    private var num4 = 0

    private var sum = 0
    private var prod = 0



    private inner class SumButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            sum = num1 + num2 + num3 + num4

            intent.putExtra("SUM", sum)
            setResult(RESULT_OK, intent)

            finish()
        }
    }

    private inner class ProdButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            prod = num1 * num2 * num3 * num4
            intent.putExtra("PROD", prod)
            setResult(RESULT_OK, intent)

            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_secondary)

        num1 = intent.getIntExtra("TEXT1", 0)
        num2 = intent.getIntExtra("TEXT2", 0)
        num3 = intent.getIntExtra("TEXT3", 0)
        num4 = intent.getIntExtra("TEXT4", 0)

        sumButton = findViewById(R.id.sum_button)
        prodButton = findViewById(R.id.product_button)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)

        text1.setText(num1.toString())
        text2.setText(num2.toString())
        text3.setText(num3.toString())
        text4.setText(num4.toString())

        sumButton.setOnClickListener(SumButtonListener())
        prodButton.setOnClickListener(ProdButtonListener())
    }
}