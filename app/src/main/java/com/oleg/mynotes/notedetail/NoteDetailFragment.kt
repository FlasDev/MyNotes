package com.oleg.mynotes.notedetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.oleg.mynotes.R
import com.oleg.mynotes.utilities.InjectorsUntils
import kotlinx.android.synthetic.main.fragment_note_detail.*


class NoteDetailFragment : Fragment(), NoteDetailContract.View {


    val actionListener: NoteDetailContract.UserActionListener by lazy {
        NoteDetailPresenter(InjectorsUntils.provideNoteRepository(context!!), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_note_detail, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        val noteId = arguments?.getLong(ARGUMENT_NOTE_ID)
        actionListener.openNote(noteId!!)
    }

    override fun setNoteTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar!!.title = title
    }

    override fun setNoteDetail(detail: String) {
        note_detail_description.text = detail
    }

    companion object {
        private const val ARGUMENT_NOTE_ID = "NOTE_ID"
        fun newInstance(noteId: Long): NoteDetailFragment{
            val arguments = Bundle()
            arguments.putLong(ARGUMENT_NOTE_ID, noteId)
            val fragment = NoteDetailFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

}
