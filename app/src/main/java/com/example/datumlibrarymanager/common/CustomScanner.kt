package com.example.datumlibrarymanager.common

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import com.google.zxing.integration.android.IntentIntegrator

class CustomScanner(activity: Activity) {
    private val rootActivity: Activity = activity

    fun initiateScan() {

        val integrator = IntentIntegrator(rootActivity)
        integrator
            .setCaptureActivity(CaptureActivityPortrait::class.java)
            .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
            .setPrompt("Scan a barcode.")
            .setCameraId(getFrontCameraId()) // ASAP use front-facing camera
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
