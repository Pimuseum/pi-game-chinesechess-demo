package com.pimuseum.game.chinesechess.engine.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.pimuseum.game.chinesechess.engine.constant.Res
import com.pimuseum.game.chinesechess.engine.screen.ChessVersusScreen

/**
 * Desc : ChessVersusGame
 * Author : Jiervs
 * Date : 2019/5/16
 */
class ChessVersusGame : Game() {

    var gameWidth: Float = 0F
    var gameHeight: Float = 0F

    private lateinit var chessVersusScreen: ChessVersusScreen

    override fun create() {

        // calculate width & height for game
        gameWidth = 480F
        gameHeight = Gdx.graphics.height * gameWidth / Gdx.graphics.width

        Gdx.app.log(Res.ChessLog, "World Size: $gameWidth * $gameHeight")

        //create screen
        chessVersusScreen = ChessVersusScreen(this)
        //set screen
        setScreen(chessVersusScreen)
    }
}