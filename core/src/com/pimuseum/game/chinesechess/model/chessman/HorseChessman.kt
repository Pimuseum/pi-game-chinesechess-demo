package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic
import kotlin.math.abs

/**
 * Desc : HorseChessman(马)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class HorseChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //马走日，横向移动距离2，纵向为1 或者 横向移动距离1，纵向为2
        return ((abs(nextPosition.column -position.column) == 1 && abs(nextPosition.row -position.row) == 2)
                || (abs(nextPosition.column -position.column) == 2 && abs(nextPosition.row -position.row) == 1))
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {

        //先判断是行向蹩脚还是列向蹩脚
        if (abs(nextPosition.row - position.row) == 2) {//行蹩脚
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