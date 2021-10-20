package com.rohanrj.notpad.util

import androidx.appcompat.app.AppCompatActivity
import com.rohanrj.notpad.adapter.NotesAdapter

private val adapter: Any
    get() {

    }

class HomeActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        (adapter as NotesAdapter).refresh()
    }

    fun startActivity() {

    }


}
