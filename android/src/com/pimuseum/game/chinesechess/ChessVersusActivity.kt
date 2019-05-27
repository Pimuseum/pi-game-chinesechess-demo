package com.pimuseum.game.chinesechess

import android.os.Bundle

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.pimuseum.game.chinesechess.engine.game.ChessVersusGame
import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.companion.ChessType

class ChessVersusActivity : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ChessHelper.setMyRoleType(ChessType.Black)
        initialize(ChessVersusGame(), AndroidApplicationConfiguration())
    }
}
