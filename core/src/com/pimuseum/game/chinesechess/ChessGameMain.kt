package com.pimuseum.game.chinesechess

import com.badlogic.gdx.Game
import com.pimuseum.game.chinesechess.screen.PlayScreen

/**
 * 界面链接场景
 */
class ChessGameMain : Game() {

    override fun create() {

        setScreen(PlayScreen())

    }

}
