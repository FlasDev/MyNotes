package com.oleg.mynotes.addnote.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.oleg.mynotes.addnote.AddNoteFragment
import com.oleg.mynotes.data.Date
import java.util.*

class AddNoteDatePickerDialog: DialogFragment(), DatePickerDialog.OnDateSetListener{

    lateinit var listener: AddNoteDatePickerDialog.onDialogNotificationsSave

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            listener = targetFragment as onDialogNotificationsSave
        }catch (e: ClassCastException){
            throw ClassCastException(targetFragment.toString()
                    + " must implement onDialogNotificationsSave")

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(activity!!, this, year, month, day)

        return dialog

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val time = AddNoteTimePickerDialog(Date(year, month, dayOfMonth))
        listener.saveTimeNotification(Date(year, month, dayOfMonth))
        //time.show(activity?.su, "timepicker")
    }

    interface onDialogNotificationsSave{
        fun saveTimeNotification(date: Date)
    }

    companion object {
        const val REQUEST_WEIGHT = 1
    }
}