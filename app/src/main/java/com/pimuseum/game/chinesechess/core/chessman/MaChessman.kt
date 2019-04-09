package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.support.ChessType
import com.pimuseum.game.chinesechess.core.support.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

/**
 * Desc : MaChessman(马)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class MaChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //马走日，横向移动距离2，纵向为1 或者 横向移动距离1，纵向为2
        return ((Math.abs(nextPosition.column -position.column) == 1 && Math.abs(nextPosition.row -position.row) == 2)
                || (Math.abs(nextPosition.column -position.column) == 2 && Math.abs(nextPosition.row -position.row) == 1))
    }

    override fun chessboardRule(chessmanList: ArrayList<Chessman>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessmanList,nextPosition)?.let { chessman->
            if (chessman.chessType == this@MaChessman.chessType) return false//同色棋子不能被吃
        }

        return true
    }

    override fun chessmanName(): String {
        return "马"
    }
}