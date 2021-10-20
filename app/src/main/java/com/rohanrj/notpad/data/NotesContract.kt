package com.rohanrj.notpad.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

@Suppress("LocalVariableName")
class NotesOpenHelper(context: Context) : SQLiteOpenHelper(context, "notes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = ""
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val SQL_DELETE_ENTRIES = ""
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
