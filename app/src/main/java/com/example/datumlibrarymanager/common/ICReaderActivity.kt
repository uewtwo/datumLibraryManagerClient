package com.example.datumlibrarymanager.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.datumlibrarymanager.R
import com.example.datumlibrarymanager.api.ApiClientManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_icreader.*


class ICReaderActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icreader)

        httpTest1.setOnClickListener {
            val zipcode = "1160014"
            compositeDisposable.clear()
            compositeDisposable.add(
                ApiClientManager.apiClient.getZip(zipcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        val jData = ApiClientManager.adapter.toJson(result)
                        Log.d(TAG, jData)
                    }, { error ->
                        Log.d(TAG, error.message.toString())
                    }, {
                        Log.d(TAG, "get completed.")
                    })
            )
//            var hoge = ""
//            ApiClientManager.apiClient
//                .getZip("1160014")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({res ->
//                    hoge = res.toString()
//                    val mes = "hogehoge"
//                    Log.d(TAG, mes)
//                }, {}, {
//                    Log.d(TAG, "completed")
//                })
//            Log.d(TAG, hoge)
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        val TAG = ApiClientManager::class.java.simpleName
    }
}
