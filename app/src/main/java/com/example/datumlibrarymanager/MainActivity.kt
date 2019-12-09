package com.example.datumlibrarymanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onResume() {
        super.onResume()

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
