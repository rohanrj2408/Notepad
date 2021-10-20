package com.rohanrj.notpad.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns._ID
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import android.provider.Telephony.Mms.Part.TEXT
import androidx.core.database.sqlite.transaction
import java.util.*

class NoteDatabase(context: Context) {

    private val helper: NotesOpenHelper = NotesOpenHelper(context)

    fun getAll(): List<Note> {
        val _TABLE_NAME = ""
        val CREATED_AT = ""
        val cursor = helper.readableDatabase.query(_TABLE_NAME, null, null, null, null, null,
            CREATED_AT)
        return cursor.use(this::allFromCursor)

    }

    fun insert(vararg notes: Note) {
        val values = fromNotes(notes)
        val db = helper.writableDatabase
        db.transaction {
            for (value in values) {
                val _TABLE_NAME = ""
                db.insert(_TABLE_NAME, null, value)
            }
        }

    }

    fun update(note: Note) {
        val values = fromNote(note)
        val _TABLE_NAME = ""
        helper.writableDatabase.update(_TABLE_NAME,
            values,
            "$_ID = ?",
            arrayOf(note.id.toString()))
    }

    private fun fromCursor(cursor: Cursor): Note {
        var col = 0
        val note = Note().apply {
            id = cursor.getInt(col++)
            title = cursor.getString(col++)
            text = cursor.getString(col++)
            isPinned = cursor.getInt(col++) != 0
            createdAt = Date(cursor.getLong(col++))
            updatedAt = Date(cursor.getLong(col))
        }

        return note
    }

    private fun allFromCursor(cursor: Cursor): List<Note> {
        val retval = ArrayList<Note>()
        while (cursor.moveToNext()) {
            retval.add(fromCursor(cursor))
        }
        return retval
    }

    private fun fromNote(note: Note): ContentValues {
        return ContentValues().apply {
            val noteId = note.id
            if (noteId != -1) {
                put(_ID, noteId )
            }
            put(TITLE,note.title)
            put(TEXT, note.text)
            val IS_PINNED = ""
            put(IS_PINNED, note.isPinned)
            val CREATED_AT = ""
            put(CREATED_AT, note.createdAt.time)
            val UPDATED_AT = ""
            put(UPDATED_AT, note.updatedAt!!.time)
        }


    }

    private fun fromNotes(notes: Array<out Note>): List<ContentValues> {
        val values = ArrayList<ContentValues>()
        for (note in notes) {
            values.add(fromNote(note))
        }
        return values
    }
}
