package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.support.ChessType
import com.pimuseum.game.chinesechess.core.support.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

/**
 * Desc : PawnsChessman(帅,将)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class KingChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //帅,将移动距离为1
       if ((Math.abs(nextPosition.column - position.column) + Math.abs(nextPosition.row - position.row)) != 1) return false

        return if (chessType == ChessType.Red) {//红棋子
            //帅,将只能在九宫格内移动
            nextPosition.column in 4..6 && nextPosition.column in 1..3

        } else {//黑棋子

            nextPosition.column in 4..6 && nextPosition.column in 8..10
        }
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@KingChessman.chessType) return false//同色棋子不能被吃
        }
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "帅"
            ChessType.Black -> "将"
        }
    }
}