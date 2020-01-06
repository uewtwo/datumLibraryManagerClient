package com.example.datumlibrarymanager.borrow

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.datumlibrarymanager.R
import com.example.datumlibrarymanager.common.CustomBarcodeScanner
import com.example.datumlibrarymanager.common.DatePickerDialogFragment
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_borrow.*
import java.text.SimpleDateFormat
import java.util.*

class BorrowActivity : AppCompatActivity(), DatePickerDialogFragment.OnDateSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)
    }

    override fun onResume() {
        super.onResume()
        // Read library-book's barcode
        buttonCameraForBorrow.setOnClickListener {
            val scanner = CustomBarcodeScanner(this)
            scanner.initiateScan()
        }

        // TODO: ic card リーダーに対するアクション

        // Pick date for returning deadline.
        viewDate.setText(getFormattedDate(Date()))

        buttonPickDate.setOnClickListener {
            DatePickerDialogFragment
                .newInstance()
                .show(supportFragmentManager, DatePickerDialogFragment::class.java.simpleName)
        }
    }
    override fun onSelected(year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        viewDate.setText(getFormattedDate(calendar.time))
    }

    private fun getFormattedDate(date: Date): String {
        val dateViewFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
        return dateViewFormat.format(date)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents != null) {
            val resultView: AppCompatTextView = titleBook
            resultView.text = "(仮):" + result.contents
        }
    }
}