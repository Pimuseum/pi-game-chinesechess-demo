package com.pimuseum.game.chinesechess.engine.actor

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.chessman.Chessman
import com.pimuseum.game.chinesechess.model.companion.ChessType

/**
 * Desc : ChessmanActor
 * Author : Jiervs
 * Date : 2019/5/17
 */
class ChessmanActor : ImageActor {

    var  chessman : Chessman? = null

    constructor() : super()

    constructor(region: TextureRegion): super(region)

    constructor(region: TextureRegion,chessman : Chessman): super(region) {
        this.chessman = chessman
    }

    /**
     * 根据第一人称视角放置棋子位置
     */
    fun setCenterByRoleType(row : Int , column : Int ,
                              originLocationX : Float ,originLocationY :Float ,
                              chessboardUnitWidth : Float , chessboardUnitHeight : Float) {

        if (ChessHelper.myRoleType == ChessType.Red) {
            setCenter(
                    originLocationX + (column * chessboardUnitWidth),
                    originLocationY + (row * chessboardUnitHeight))
        } else {
            setCenter(
                    originLocationX + ((ChessHelper.ColumnCapacity - column) * chessboardUnitWidth),
                    originLocationY + ((ChessHelper.RowCapacity - row) * chessboardUnitHeight))
        }
    }
}