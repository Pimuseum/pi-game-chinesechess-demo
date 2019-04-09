package com.pimuseum.game.chinesechess.core.tools

import com.pimuseum.game.chinesechess.core.Chessman
import com.pimuseum.game.chinesechess.core.Position


object ChessmanTools {

    /**
     * 返回某列上的棋子集合
     */
    fun getChessmanByColumn(column: Int,chessmanList : ArrayList<Chessman>) : ArrayList<Chessman>{

        val result = ArrayList<Chessman>()
        for (chessman in chessmanList) {
            if (chessman.position.column == column) {
                result.add(chessman)
            }
        }
        return result
    }

    /**
     * 返回某行上的棋子集合
     */
    fun getChessmanByRow(row: Int,chessmanList : ArrayList<Chessman>) : ArrayList<Chessman>{

        val result = ArrayList<Chessman>()
        for (chessman in chessmanList) {
            if (chessman.position.row == row) {
                result.add(chessman)
            }
        }
        return result
    }

    /**
     * 根据 目标 postion 判断是否存在棋子,如果存在则返回该棋子
     */
    fun isExistChessmanByPosition(chessmanList : ArrayList<Chessman>,position: Position) : Chessman? {

        for (chessman in chessmanList) {
            if (chessman.position.column == position.column
                && chessman.position.row == position.row) {
                return chessman
            }
        }

        return null
    }

    /**
     * 根据 两点连线间存在几个其他棋子
     */
    fun chessNumberBetweenPositions(chessmanList : ArrayList<Chessman>,position1: Position,position2: Position) : Int {

        var count : Int = 0

        if ((position1.column == position2.column && position1.row == position1.row)
            ||(position1.column != position2.column && position1.row != position1.row)) {
            //两点是同一个点，或者两点不在同一条直线上
            return -1
        }

        if (position1.column == position2.column) {//列相等
            val queryColumn = position1.column
            for (chessman in getChessmanByColumn(queryColumn,chessmanList)) {

                if (position1.row > position2.row) {
                    if (chessman.position.row > position2.row && chessman.position.row < position1.row) count ++
                } else {
                    if (chessman.position.row > position1.row && chessman.position.row < position2.row) count ++
                }
            }

        } else {//行相等
            val queryRow = position1.row
            for (chessman in getChessmanByRow(queryRow,chessmanList)) {

                if (position1.column > position2.column) {
                    if (chessman.position.column > position2.column && chessman.position.column < position1.row) count ++
                } else {
                    if (chessman.position.column > position1.column && chessman.position.column < position2.column) count ++
                }
            }
        }

        return count
    }
}