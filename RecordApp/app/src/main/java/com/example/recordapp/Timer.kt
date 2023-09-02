package com.example.recordapp

import android.os.Handler
import android.os.Looper

class Timer(listener: OnTimerTickListener) {

    private var duration = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable = object: Runnable {
        override fun run() {
            duration += 40L
            listener.onTick(duration)
            handler.postDelayed(this,40L)
        }

    }

    fun start(){
        handler.postDelayed(runnable, 40L)
    }

    fun stop(){
        handler.removeCallbacks(runnable)
        duration = 0
    }


}

interface OnTimerTickListener {
    fun onTick(duration: Long)
}