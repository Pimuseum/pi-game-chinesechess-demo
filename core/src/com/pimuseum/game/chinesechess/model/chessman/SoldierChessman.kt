package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic

/**
 * Desc : SoldierChessman(兵,卒)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class SoldierChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //兵卒移动距离为1
       if ((Math.abs(nextPosition.column - position.column) + Math.abs(nextPosition.row - position.row)) != 1) return false

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

        ChessLogic.isExistChessman(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@SoldierChessman.chessType) return false//同色棋子不能被吃
        }
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "兵"
            ChessType.Black -> "卒"
        }
    }
}