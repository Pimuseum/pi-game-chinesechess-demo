package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic
import kotlin.math.abs

/**
 * Desc : GeneralChessman(帅,将)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class GeneralChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //帅,将移动距离为1
       if ((abs(nextPosition.column - position.column) + abs(nextPosition.row - position.row)) != 1) return false

        return if (chessType == ChessType.Red) {//红棋子
            //帅,将只能在九宫格内移动
            nextPosition.column in 4..6 && nextPosition.row in 1..3

        } else {//黑棋子

            nextPosition.column in 4..6 && nextPosition.row in 8..10
        }
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "帅"
            ChessType.Black -> "将"
        }
    }
}