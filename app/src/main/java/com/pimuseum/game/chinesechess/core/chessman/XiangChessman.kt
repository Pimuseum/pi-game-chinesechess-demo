package com.pimuseum.game.chinesechess.core.chessman

import com.pimuseum.game.chinesechess.core.ChessType
import com.pimuseum.game.chinesechess.core.Chessman
import com.pimuseum.game.chinesechess.core.Position
import com.pimuseum.game.chinesechess.core.tools.ChessmanTools

/**
 * Desc : XiangChessman(相，象)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class XiangChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //象走田,移动后列行移动距离为2
        if (Math.abs(nextPosition.column - position.column) != 2 || Math.abs(nextPosition.row - position.row) != 2) return false

        return if (chessType == ChessType.Red) {//红棋子

            //象不能过河
            listOf(1,3,5).any { it ==  nextPosition.row }

        } else {//黑棋子

            listOf(6,8,10).any { it ==  nextPosition.row }
        }
    }

    override fun chessboardRule(chessmanList: ArrayList<Chessman>, nextPosition: Position): Boolean {

        ChessmanTools.isExistChessmanByPosition(chessmanList,nextPosition)?.let { chessman->
            if (chessman.chessType == this@XiangChessman.chessType) return false//同色棋子不能被吃
        }

        //计算田心坐标,这里行列坐标差值都是2，棋子约束决定，所以计算出来的中心坐标不存在小数
        val centerColumn = (this@XiangChessman.position.column + nextPosition.column)/2
        val centerRow = (this@XiangChessman.position.row + nextPosition.row)/2

        ChessmanTools.isExistChessmanByPosition(chessmanList, Position(centerRow,centerColumn))?.let { chessman->
            return false
        }
        return true
    }

    override fun chessmanName(): String {

        return when(chessType) {
            ChessType.Red -> "相"
            ChessType.Black -> "象"
        }
    }
}