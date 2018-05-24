package com.nmwilkinson.recycler

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    private val random = Random(SystemClock.uptimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = ColorAdapter()
        list.adapter = adapter

        add.setOnClickListener {
            adapter.insertColor(randomColor())
            list.scrollToPosition(0)
        }

        add.setOnLongClickListener {
            adapter.clearData()
            true
        }
    }

    private fun randomColor(): Int {
        return (0xFF000000 or random.nextInt(0x00FFFFFF).toLong()).toInt()
    }
}
