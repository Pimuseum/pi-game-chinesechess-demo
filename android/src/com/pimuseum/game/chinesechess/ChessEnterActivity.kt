package com.pimuseum.game.chinesechess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView


/**
 * Desc : ChessEnterActivity
 * Author : Jiervs
 * Date : 2019/5/24
 */
class ChessEnterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        findViewById<TextView>(R.id.tvSelfPractice).setOnClickListener {
            startActivity(Intent(this, ChessVersusActivity::class.java))
        }
    }
}