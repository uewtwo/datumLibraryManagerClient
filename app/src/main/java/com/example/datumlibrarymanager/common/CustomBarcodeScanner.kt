package com.example.datumlibrarymanager.common

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class CustomBarcodeScanner(activity: AppCompatActivity) {
    private val rootActivity: AppCompatActivity = activity

    fun initiateScan(message: String = "Scan a barcode.") {
        val integrator = IntentIntegrator(rootActivity)
        integrator
            .setCaptureActivity(CaptureActivityPortrait::class.java)
            .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
            .setPrompt(message)
            .setCameraId(getFrontCameraId())
            .setBeepEnabled(false)
            .setBarcodeImageEnabled(false)
            .initiateScan()
    }

    private fun getFrontCameraId(): Int {
        val cameraMng = rootActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        // detect front camera.
        var frontCameraId = -1
        val ids: Array<String> = cameraMng.cameraIdList
        for (id in ids) {
            val cameraChars = cameraMng.getCameraCharacteristics(id)
            if (cameraChars.get(CameraCharacteristics.LENS_FACING)
                    == CameraCharacteristics.LENS_FACING_FRONT) {
                // Front.
                frontCameraId = id.toInt()
                break
            }
        }

        return frontCameraId
    }


}
