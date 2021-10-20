package com.rohanrj.notpad.data

import android.content.Context

object DataStore {

    @JvmStatic
    lateinit var notes: NoteDatabase
        private set

    fun init(context: Context) {
        notes = NoteDatabase(context)
    }

    fun execute(runnable: Runnable) {
        execute { runnable.run() }
    }

    fun execute(fn: () -> Unit){
        doAsync()
    }

    private fun doAsync() {

    }
}