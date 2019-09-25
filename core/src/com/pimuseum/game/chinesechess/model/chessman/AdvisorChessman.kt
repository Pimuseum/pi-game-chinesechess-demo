package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic
import kotlin.math.abs

/**
 * Desc : AdvisorChessman(士，仕)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class AdvisorChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //士按斜线走九宫格，每次移动列横各移动为1
        if (!(abs(nextPosition.column -position.column) == 1 && abs(nextPosition.row -position.row) == 1)) return false

        return if (chessType == ChessType.Red) {//红棋子

            (nextPosition.column == 4 && nextPosition.row == 1) ||
            (nextPosition.column == 4 && nextPosition.row == 3) ||
            (nextPosition.column == 5 && nextPosition.row == 2) ||
            (nextPosition.column == 6 && nextPosition.row == 1) ||
            (nextPosition.column == 6 && nextPosition.row == 3)

        } else {//黑棋子

            (nextPosition.column == 4 && nextPosition.row == 10) ||
            (nextPosition.column == 4 && nextPosition.row == 8)  ||
            (nextPosition.column == 5 && nextPosition.row == 9) ||
            (nextPosition.column == 6 && nextPosition.row == 10) ||
            (nextPosition.column == 6 && nextPosition.row == 8)
        }
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "士"
            ChessType.Black -> "仕"
        }
    }
}