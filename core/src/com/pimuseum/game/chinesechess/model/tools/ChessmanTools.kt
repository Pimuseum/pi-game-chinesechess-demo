package com.pimuseum.game.chinesechess.model.tools

import com.pimuseum.game.chinesechess.core.chessman.*
import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.chessman.Chessman
import com.pimuseum.game.chinesechess.model.chessman.JuChessman
import com.pimuseum.game.chinesechess.model.chessman.KingChessman
import com.pimuseum.game.chinesechess.model.chessman.MaChessman
import com.pimuseum.game.chinesechess.model.chessman.PaoChessman
import com.pimuseum.game.chinesechess.model.chessman.ShiChessman
import com.pimuseum.game.chinesechess.model.chessman.XiangChessman
import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position


object ChessmanTools {

    /**
     * 根据 目标 postion 判断是否存在棋子,如果存在则返回该棋子
     */
    fun isExistChessmanByPosition(chessboardInfo : Array<Array<Chessman?>>, position: Position) : Chessman? {
        chessboardInfo[position.row][position.column]?.let { chessman ->
            return chessman
        }
        return null
    }

    /**
     * 根据 两点连线间存在几个其他棋子
     */
    fun chessNumberBetweenPositions(chessboardInfo : Array<Array<Chessman?>>, position1: Position, position2: Position) : Int {

        var count : Int = 0

        if ((position1.column == position2.column && position1.row == position1.row)
            ||(position1.column != position2.column && position1.row != position1.row)) {
            //两点是同一个点，或者两点不在同一条直线上
            return -1
        }

        if (position1.row == position2.row) {//行相等

            val queryRow = position1.row
            if (position1.column > position2.column) {
                for (queryColumn in position2.column + 1 until position1.column) {
                    if (chessboardInfo[queryRow][queryColumn] != null) count ++
                }
            } else {
                for (queryColumn in position1.column + 1 until position2.column) {
                    if (chessboardInfo[queryRow][queryColumn] != null) count ++
                }
            }

        } else {//列相等

            val queryColumn = position1.column
            if (position1.row > position2.row) {
                for (queryRow in position2.row + 1 until position1.row) {
                    if (chessboardInfo[queryRow][queryColumn] != null) count ++
                }
            } else {
                for (queryRow in position1.row + 1 until position2.row) {
                    if (chessboardInfo[queryRow][queryColumn] != null) count ++
                }
            }
        }

        return count
    }

    /**
     * 根据具体棋子 Class 和 Type 查询加载的资源路径
     */
    fun queryResPathByChessman(chessman : Chessman) : String? {

        return if (chessman.chessType == ChessType.Red) {//Red Chessman

            when(chessman.javaClass) {
                JuChessman::class.java -> "actor/chessman_rj.png"
                MaChessman::class.java -> "actor/chessman_rm.png"
                XiangChessman::class.java -> "actor/chessman_rx.png"
                ShiChessman::class.java -> "actor/chessman_rs.png"
                KingChessman::class.java -> "actor/chessman_rk.png"
                PaoChessman::class.java -> "actor/chessman_rp.png"
                PawnChessman::class.java -> "actor/chessman_rpawn.png"
                else -> null
            }
        } else {
            when(chessman.javaClass) {//Black Chessman
                JuChessman::class.java -> "actor/chessman_bj.png"
                MaChessman::class.java -> "actor/chessman_bm.png"
                XiangChessman::class.java -> "actor/chessman_bx.png"
                ShiChessman::class.java -> "actor/chessman_bs.png"
                KingChessman::class.java -> "actor/chessman_bk.png"
                PaoChessman::class.java -> "actor/chessman_bp.png"
                PawnChessman::class.java -> "actor/chessman_bpawn.png"
                else -> null
            }
        }
    }
}