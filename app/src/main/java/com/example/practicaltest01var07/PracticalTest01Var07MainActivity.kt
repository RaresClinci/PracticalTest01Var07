package com.example.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import java.util.Random
import java.util.StringTokenizer

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    private lateinit var setButton: Button
    private lateinit var randomButton: Button
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText

    private val random = Random()
    private val REQUEST_CODE_SECONDARY = 100

    private var Sum = 0
    private var product = 0

    private final val SUM_KEY = "LEFT_KEY"
    private final val PROD_KEY = "RIGHT_KEY"

    private inner class Listener : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.getStringExtra(BROADCAST_RECEIVER_EXTRA)

            val numbers = StringTokenizer(message, " ")

            text1.setText(numbers.nextToken().toString())
            text2.setText(numbers.nextToken().toString())
            text3.setText(numbers.nextToken().toString())
            text4.setText(numbers.nextToken().toString())
        }
    }
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

    private inner class SetButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            intent = Intent(
                this@PracticalTest01Var07MainActivity,
                PracticalTest01Var07SecondaryActivity::class.java
            )
            intent.putExtra("TEXT1", text1.text.toString().toIntOrNull() ?: 0)
            intent.putExtra("TEXT2", text2.text.toString().toIntOrNull() ?: 0)
            intent.putExtra("TEXT3", text3.text.toString().toIntOrNull() ?: 0)
            intent.putExtra("TEXT4", text4.text.toString().toIntOrNull() ?: 0)

            startActivityForResult(intent, REQUEST_CODE_SECONDARY)
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
        setButton.setOnClickListener(SetButtonListener())

        val intent = Intent(this@PracticalTest01Var07MainActivity, PracticalTest01Var07Service::class.java)
        startService(intent)

        val receiver = Listener()
        val filter = IntentFilter().apply{addAcction("ro.pub.cs.systems.eim.practicaltest01var07.randomnumbers")}


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SECONDARY) {
            if (resultCode == Activity.RESULT_OK){
                var sum = data?.getIntExtra("SUM", -1)
                var prod = data?.getIntExtra("SUM", -1)
                Sum = sum!!
                product = prod!!

                if (sum != -1) {
                    Toast.makeText(this, sum.toString(), Toast.LENGTH_SHORT).show()
                }
                if (prod != -1) {
                    Toast.makeText(this, prod.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SUM_KEY, Sum.toString())
        outState.putString(PROD_KEY, product.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(SUM_KEY)) {
            Sum = savedInstanceState.getString(SUM_KEY)?.toIntOrNull() ?: 0
            Log.d("[SUM]",Sum.toString())
            Toast.makeText(this, Sum.toString(), Toast.LENGTH_SHORT).show()
        }
        if (savedInstanceState.containsKey(PROD_KEY)) {
            product = savedInstanceState.getString(SUM_KEY)?.toIntOrNull() ?: 0
            Log.d("[PROD]",product.toString())
            Toast.makeText(this, product.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}