package com.example.datumlibrarymanager.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.datumlibrarymanager.R
import com.example.datumlibrarymanager.common.CustomBarcodeScanner
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun onResume() {
        super.onResume()

        buttonCameraForRental.setOnClickListener {
            scanBarcode()
        }
    }

    private fun scanBarcode() {
        val scanner = CustomBarcodeScanner(this)
        scanner.initiateScan("Scan a barcode for registering.")
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents != null) {
            val resultView: AppCompatEditText = barcodeReadResult
            resultView.setText(result.contents)
        }
    }

    companion object {
        const val CAMERA_RETURN_CODE = 1
        const val CAMERA_PERMISSION_REQUEST_CODE = 2
    }
}
