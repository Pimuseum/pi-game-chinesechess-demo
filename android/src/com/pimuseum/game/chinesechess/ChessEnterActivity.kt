package com.pimuseum.game.chinesechess

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
 * Desc : ChessEnterActivity
 * Author : Jiervs
 * Date : 2019/5/24
 */
class ChessEnterActivity : Activity() {

    private lateinit var soundPool: SoundPool
    private var soundId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chess_enter)
        init()
        setOnClick()
    }

    private fun init() {

        val attr = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build()

        soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(attr)
                .build()

        soundId = soundPool.load(assets.openFd("sound/pick.mp3"),1)
    }

    private fun setOnClick() {
        findViewById<TextView>(R.id.tvSelfPractice).setOnClickListener {
            it.noFastClick()
            soundPool.play(soundId, 1F, 1F, 1, 0, 1F)
            startActivity(Intent(this, ChessVersusActivity::class.java))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        soundPool.autoPause()
        soundPool.release()
    }

    private fun View.noFastClick() {
        this.isClickable = false
        this.postDelayed({this.isClickable = true},500)
    }
}