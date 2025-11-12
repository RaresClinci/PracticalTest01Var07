package com.example.practicaltest01var07

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Random

const val BROADCAST_RECEIVER_EXTRA = "broadcast_receiver_extra"
const val PROCESSING_THREAD_TAG = "[Processing Thread]"

class ProccesingThread(private val context: Context) :
    Thread(){
    private var isRunning = true

    private val random = Random()


    override fun run() {

        while(isRunning) {
            sendMessage()
            sleep()
        }

        Log.d(PROCESSING_THREAD_TAG, "Thread has stopped!")
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.setAction("ro.pub.cs.systems.eim.practicaltest01var07.randomnumbers")
        intent.putExtra(
            BROADCAST_RECEIVER_EXTRA,
            random.nextInt().toString() + " " + random.nextInt().toString() + " " + random.nextInt().toString() + " " + random.nextInt().toString()
        )
        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(1000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }


}