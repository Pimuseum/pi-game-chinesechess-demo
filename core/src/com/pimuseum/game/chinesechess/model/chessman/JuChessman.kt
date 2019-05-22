package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.tools.ChessTools

/**
 * Desc : JuChessman(车)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class JuChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {


    override fun chessmanRule(nextPosition: Position): Boolean {

        //车列行直走
        return ((nextPosition.column == position.column && nextPosition.row != position.row)

                || (nextPosition.column != position.column && nextPosition.row == position.row))
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {

        ChessTools.isExistChessmanByPosition(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@JuChessman.chessType) return false//同色棋子不能被吃
        }

        if (ChessTools.chessNumberBetweenPositions(chessboardInfo,this@JuChessman.position,nextPosition) > 0) {
            //两棋子之间有其他棋子则不符合 车的走法
            return false
        }

        return true
    }

    override fun chessmanName(): String {
        return "车"
    }
}