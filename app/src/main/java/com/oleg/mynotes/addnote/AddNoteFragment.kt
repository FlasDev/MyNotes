package com.oleg.mynotes.addnote


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView

import com.oleg.mynotes.R
import com.oleg.mynotes.addnote.dialogs.AddNoteDatePickerDialog
import com.oleg.mynotes.addnote.dialogs.AddNoteTimePickerDialog
import com.oleg.mynotes.data.Date
import com.oleg.mynotes.utilities.InjectorsUntils
import kotlinx.android.synthetic.main.fragment_add_note.*


@Suppress("UNUSED_EXPRESSION")
class AddNoteFragment : Fragment(), AddNoteContract.View, AddNoteDatePickerDialog.onDialogNotificationsSave{

    private lateinit var actionListener: AddNoteContract.UserActionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    @SuppressLint("CommitPrefEdits")
    override fun onResume() {
        super.onResume()
        loadShared()
    }

    override fun onPause() {
        super.onPause()
        saveShared()

    }

    override fun saveTimeNotification(date: Date) {
        Log.d("myLogs", date.toString())
    }

    override fun saveShared() {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.save_description), MODE_PRIVATE)?:return
        with(sharedPref.edit()){
            putString(getString(R.string.saved_note_title), add_note_title.text.toString())
            putString(getString(R.string.saved_note_detail), add_note_description.text.toString())
            apply()
        }
    }
    override fun loadShared() {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.save_description),MODE_PRIVATE) ?: return
        val title = sharedPref.getString(getString(R.string.saved_note_title), "")
        val detail = sharedPref.getString(getString(R.string.saved_note_detail), "")
        add_note_title.setText(title)
        add_note_description.setText(detail)
    }

    override fun clearShared() {
        activity?.runOnUiThread {
            val sharedPref = activity?.getSharedPreferences(getString(R.string.save_description),MODE_PRIVATE)
            sharedPref?.edit()?.clear()?.apply()
        }
    }


    override fun showNotesList() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add_note_menu, menu)
        menu?.findItem(R.id.add_note_notification)?.setOnMenuItemClickListener{
            this.openDatePicker()
            true
        }
    }

    override fun openDatePicker() {
        val datePicker = AddNoteDatePickerDialog()
        datePicker.setTargetFragment(this, AddNoteDatePickerDialog.REQUEST_WEIGHT)
        datePicker.show(activity?.supportFragmentManager, "datePicker")
    }

    override fun showEmptyNoteError() {
        Snackbar.make(add_note_title, getString(R.string.empty_note_message), Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = AddNoteFragment()
    }
}
