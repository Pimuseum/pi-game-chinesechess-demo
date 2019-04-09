package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.ChessType
import com.pimuseum.game.chinesechess.core.Chessman
import com.pimuseum.game.chinesechess.core.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

/**
 * Desc : PawnsChessman(兵,卒)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class PawnsChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //兵卒移动距离为1
       if ((Math.abs(nextPosition.column - position.column) + Math.abs(nextPosition.row - position.row)) != 1) return false

        return if (chessType == ChessType.Red) {//红棋子
            //兵卒只能向前走和平移
            nextPosition.row >= position.row

        } else {//黑棋子

            nextPosition.row <= position.row
        }
    }

    override fun chessboardRule(chessmanList: ArrayList<Chessman>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessmanList,nextPosition)?.let { chessman->
            if (chessman.chessType == this@PawnsChessman.chessType) return false//同色棋子不能被吃
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