package com.pimuseum.game.chinesechess.engine.stage

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.pimuseum.game.chinesechess.engine.actor.ImageActor
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.tools.ChessmanTools
import com.pimuseum.game.chinesechess.engine.actor.ChessBoardActor
import com.pimuseum.game.chinesechess.engine.actor.ChessmanActor

/**
 * Desc : ChessVersusStage
 * Author : Jiervs
 * Date : 2019/5/16
 */
class ChessVersusStage( viewport: Viewport) : Stage(viewport) {

    private var chessboardWidth : Float = 0F
    private var chessboardHeight : Float = 0F

    private var chessboardLogicWidth : Float = 0F
    private var chessboardLogicHeight : Float = 0F

    private var chessboardUnitWidth : Float = 0F
    private var chessboardUnitHeight : Float = 0F

    private var chessmanSize : Float = 0F

    private var originLocationX = 0F
    private var originLocationY = 0F

    private lateinit var bgActor : ImageActor
    private lateinit var chessboardActor : ChessBoardActor
    private var chessmanActors = ArrayList<ChessmanActor>()

    init {
        //chessboard bg size
        chessboardWidth = width * (9F / 10F)
        chessboardHeight = width * ( 9F / 10F) * (578F / 510F)

        //chessboard logic size
        chessboardLogicWidth = chessboardWidth * ((510F - 2 * 33F) / 510F)
        chessboardLogicHeight = chessboardHeight * ((578F - 2 * 40F) / 578F)

        //chessboard unit size (not square)
        chessboardUnitWidth = chessboardLogicWidth / 8
        chessboardUnitHeight = chessboardLogicHeight / 9

        chessmanSize = chessboardUnitWidth * 9 / 10

        //ensure logic original point location
        originLocationX = (width / 2F) - (5F * chessboardUnitWidth)
        originLocationY = (height / 2F) - (5.5F * chessboardUnitHeight)

        initActors()
    }

    /**
     * Init Actors
     */
    private fun initActors(){
        //create background actor
        val bgTexture = Texture(Gdx.files.internal("actor/bg.png"))
        bgActor = ImageActor(TextureRegion(bgTexture))
        bgActor.setCenter(width / 2, height / 2)
        addActor(bgActor)

        //create chessboard actor
        val chessboardTexture = Texture(Gdx.files.internal("actor/chessboard.png"))
        chessboardActor = ChessBoardActor(TextureRegion(chessboardTexture))
        chessboardActor.setSize(chessboardWidth,chessboardHeight)
        chessboardActor.setCenter(width / 2, height / 2)
        addActor(chessboardActor)

        //create chessmen actors
        initChessmanActors()
    }

    /**
     * Create Chessman Actors
     */
    private fun initChessmanActors() {
        ChessHelper.loadChessman()
        val chessboardInfo = ChessHelper.queryChessboardInfo()

        for(row in 1..ChessHelper.RowCapacity) {
            for (column in 1..ChessHelper.ColumnCapacity) {

                chessboardInfo[row][column]?.let { chessman ->
                    //create chessman actor

                    ChessmanTools.queryResPathByChessman(chessman)?.let { resPath->
                        val chessmanTexture = Texture(Gdx.files.internal(resPath))
                        val chessmanActor = ChessmanActor(TextureRegion(chessmanTexture))
                        chessmanActor.setSize(chessmanSize,chessmanSize)
                        chessmanActor.setCenter(originLocationX + (chessman.position.column * chessboardUnitWidth),
                                originLocationY + (chessman.position.row * chessboardUnitHeight))

                        Gdx.app.log("ChessLog1", "${chessman.javaClass.simpleName}: ${chessman.position.row} *" +
                                " ${chessman.position.column}")

                        addActor(chessmanActor)
                    }
                }
            }
        }
    }
}