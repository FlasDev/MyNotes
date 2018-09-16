package com.oleg.mynotes.notes

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.oleg.mynotes.R
import com.oleg.mynotes.data.Note

class ListNoteAdapter(private var notes: List<Note>,
                      val itemListener: NoteItemListener,
                      val itemDeleteListener: NoteItemDeleteListener)
    : RecyclerView.Adapter<ListNoteAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater?.inflate(R.layout.item_note,  p0, false)
        return this.ViewHolder(noteView!!, itemListener, itemDeleteListener)
    }

    init {
        setNotes(notes)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val note = notes[p1]
        p0.bindNote(note)
    }

    fun setNotes(listNotes: List<Note>){
        notes = listNotes
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = notes[position]


    inner class ViewHolder(itemView: View,
                           itemListener: NoteItemListener,
                           itemDeleteListener: NoteItemDeleteListener)
        : RecyclerView.ViewHolder(itemView)
    {
        val title: TextView = itemView.findViewById(R.id.note_detail_title)
        val description: TextView = itemView.findViewById(R.id.note_detail_description)
        val delete: ImageButton = itemView.findViewById(R.id.note_delete)
        init {

            itemView.setOnClickListener {
                val note = getItem(adapterPosition)
                itemListener.onNoteClick(note)
            }

            delete.setOnClickListener {
                val note = getItem(adapterPosition)
                itemDeleteListener.onDeleteNoteClick(note)
            }

        }
        fun bindNote(note: Note){
            title.text = note.title
            description.text = note.description
        }
    }

    interface NoteItemListener{
        fun onNoteClick(clickedNote: Note)
    }

    interface NoteItemDeleteListener{
        fun onDeleteNoteClick(deleteNote: Note)
    }
}