package com.oleg.mynotes.addnote


import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.oleg.mynotes.R
import com.oleg.mynotes.utilities.InjectorsUntils
import kotlinx.android.synthetic.main.fragment_add_note.*


class AddNoteFragment : Fragment(), AddNoteContract.View {

    private lateinit var actionListener: AddNoteContract.UserActionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionListener = AddNotePresenter(InjectorsUntils.provideNoteRepository(context!!), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_note, container, false)
        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab_apply_note)
        fab?.setOnClickListener {
            actionListener.saveNote(
                    add_note_title.text.toString(),
                    add_note_description.text.toString())
        }
        return view
    }

    override fun showNotesList() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }

    override fun showEmptyNoteError() {
        Snackbar.make(add_note_title, getString(R.string.empty_note_message), Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = AddNoteFragment()
    }
}
