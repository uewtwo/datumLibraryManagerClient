package com.example.datumlibrarymanager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.datumlibrarymanager.`return`.ReturnActivity
import com.example.datumlibrarymanager.borrow.BorrowActivity
import com.example.datumlibrarymanager.common.ICReaderActivity
import com.example.datumlibrarymanager.register.RegisterActivity
import com.example.datumlibrarymanager.search.SearchActivity
import com.example.datumlibrarymanager.stocktake.StocktakeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // TODO: permission denied になった場合の処理
    override fun onResume() {
        super.onResume()

        checkFeature()
        setOnMainClickListener()

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == RegisterActivity.CAMERA_PERMISSION_REQUEST_CODE) {
            if (!(grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                finish()
            }
        }
    }

    // 使用アプリの存在確認，及び権限確認をまとめて行う
    private fun checkFeature() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(packageManager)?.let {
            if (!checkCameraPermission()) {
                grantCameraPermission()
            }
        } ?: Toast.makeText(this, "カメラを扱うアプリがありません", Toast.LENGTH_LONG).show()
    }

    private fun checkCameraPermission() = PackageManager.PERMISSION_GRANTED ==
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)

    private fun grantCameraPermission() =
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA),
            RegisterActivity.CAMERA_PERMISSION_REQUEST_CODE
        )


    private fun setOnMainClickListener() {
        borrowButton.setOnClickListener {
            val intent = Intent(this@MainActivity, BorrowActivity::class.java)
            startActivity(intent)
        }

        returnButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ReturnActivity::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        stocktakeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, StocktakeActivity::class.java)
            startActivity(intent)
        }

        // for debug
        icReaderButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ICReaderActivity::class.java)
            startActivity(intent)
        }
    }
}
