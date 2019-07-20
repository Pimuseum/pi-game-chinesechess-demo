package com.pimuseum.game.chinesechess.model.logic

import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.companion.Position


object ChessLogic {

    /**
     * 根据 目标 position 判断是否存在棋子,如果存在则返回该棋子
     */
    fun isExistChessman(chessboardInfo : Array<Array<Chessman?>>, position: Position) : Chessman? {
        chessboardInfo[position.row][position.column]?.let { chessman ->
            return chessman
        }
        return null
    }

    /**
     * 查询 两点连线间存在几个其他棋子
     */
    fun numberBetween2Positions(chessboardInfo : Array<Array<Chessman?>>, position1: Position, position2: Position) : Int {

        var count : Int = 0

        if ((position1.column == position2.column && position1.row == position2.row)
            ||(position1.column != position2.column && position1.row != position2.row)) {
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

}