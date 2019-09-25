package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic
import kotlin.math.abs

/**
 * Desc : SoldierChessman(兵,卒)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class SoldierChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //兵卒移动距离为1
       if ((abs(nextPosition.column - position.column) + abs(nextPosition.row - position.row)) != 1) return false

        //兵卒只能向前走和平移,过河以前不能横向移动

        return if (chessType == ChessType.Red) {//红棋子
            if (position.row <= 5) {
                nextPosition.row >= position.row && nextPosition.column == position.column
            } else {
                nextPosition.row >= position.row
            }
        } else {//黑棋子
            if (position.row >= 6) {
                nextPosition.row <= position.row && nextPosition.column == position.column
            } else {
                nextPosition.row <= position.row
            }
        }
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "兵"
            ChessType.Black -> "卒"
        }
    }
}