package com.pimuseum.game.chinesechess

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.pimuseum.game.chinesechess.engine.constant.GameMode
import com.pimuseum.game.chinesechess.engine.game.ChessVersusGame

class ChessVersusActivity : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ChessHelper.setMyRoleType(ChessType.Black)
        initialize(ChessVersusGame(GameMode.Practice), AndroidApplicationConfiguration())
    }
}
