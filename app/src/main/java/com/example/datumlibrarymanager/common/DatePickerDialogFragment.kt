package com.example.datumlibrarymanager.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date picker dialog fragment
 */

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        fun newInstance(): DatePickerDialogFragment = DatePickerDialogFragment()
    }

    interface OnDateSelectedListener {
        fun onSelected(year: Int, month: Int, day: Int)
    }
    private val calendar = Calendar.getInstance()
    private lateinit var listener: OnDateSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDateSelectedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString())
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(
            context!!,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
            .also {
                // lower limit
                it.datePicker.minDate = calendar.timeInMillis
                // upper limit
                it.datePicker.maxDate = calendar.timeInMillis.plus(1000 * 60 * 60 * 24 * 7)
                it.setTitle("")
            }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        listener.onSelected(year, month, day)
    }
}
