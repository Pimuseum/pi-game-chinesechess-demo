package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic

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

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {

        ChessLogic.isExistChessman(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@MaChessman.chessType) return false//同色棋子不能被吃
        }

        //先判断是行向蹩脚还是列向蹩脚
        if (Math.abs(nextPosition.row - position.row) == 2) {//行蹩脚
            val queryRow = (nextPosition.row + position.row)/2
            ChessLogic.isExistChessman(chessboardInfo, Position(queryRow, position.column))?.let {
                return false
            }
        } else {//列蹩脚
            val queryColumn = (nextPosition.column + position.column)/2
            ChessLogic.isExistChessman(chessboardInfo, Position(position.row, queryColumn))?.let {
                return false
            }
        }

        return true
    }

    override fun chessmanName(): String {
        return "马"
    }
}