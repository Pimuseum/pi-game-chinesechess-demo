package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.support.ChessType
import com.pimuseum.game.chinesechess.core.support.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

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

    override fun chessboardRule(chessmanList: ArrayList<Chessman>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessmanList,nextPosition)?.let { chessman->
            if (chessman.chessType == this@JuChessman.chessType) return false//同色棋子不能被吃
        }

        if (ChessmanTools.chessNumberBetweenPositions(chessmanList,this@JuChessman.position,nextPosition) > 0) {
            //两棋子之间有其他棋子则不符合 车的走法
            return false
        }

        return true
    }

    override fun chessmanName(): String {
        return "车"
    }
}