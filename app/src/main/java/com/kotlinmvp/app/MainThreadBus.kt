package com.kotlinmvp.app

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus

class MainThreadBus : Bus() {

    val handler = Handler(Looper.getMainLooper());
    override fun post(event: Any?) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post { super@MainThreadBus.post(event) }
        } else {
            super.post(event)
        }

    }
}