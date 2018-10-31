package com.oleg.mynotes.addnote.dialogs

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import android.text.format.DateFormat.is24HourFormat
import com.oleg.mynotes.data.Date

import java.util.*


@SuppressLint("ValidFragment")
class AddNoteTimePickerDialog(var date: Date): DialogFragment(), TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute,
                DateFormat.is24HourFormat(activity))

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        date = Date(hour = hourOfDay, minute = minute)
    }


}