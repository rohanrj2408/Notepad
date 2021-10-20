package com.rohanrj.notpad.adapter


import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohanrj.notpad.R
import com.rohanrj.notpad.data.DataStore
import com.rohanrj.notpad.data.Note
import com.rohanrj.notpad.util.HomeActivity
import kotlinx.android.synthetic.main.note_row_item.view.*
import java.util.*

class NotesAdapter(private val context: HomeActivity) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var notes: List<Note> = ArrayList()
    private var isRefreshing = false

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        refresh()
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id.toLong()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
            val view = context.layoutInflater.inflate(R.layout.note_row_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.text.text = note.text
        holder.title.text = note.title
        holder.noteCV.setOnClickListener{
            context.startActivity()
        }

    }

    fun refresh() {
        if (isRefreshing) return
        isRefreshing = true
        DataStore.execute {
            val notes = DataStore.notes.getAll()
            Handler(Looper.getMainLooper()).post {
                this@NotesAdapter.notes = notes
                notifyDataSetChanged()
                isRefreshing = false
            }
        }
    }

    class NotesViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteCV: View = itemView.noteCV
        val text = itemView.textTV!!
        val title: TextView = itemView.titleTV


    }
}
