package com.pimuseum.game.chinesechess.model.tools

import com.pimuseum.game.chinesechess.engine.constant.GameRes
import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position


object ChessTools {

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

    /**
     * 根据具体棋子 Class 和 Type 查询 Normal 状态下加载的资源路径
     */
    fun queryResPathByNormalChessman(chessman : Chessman?) : String? {

        chessman?.let {

            return if (chessman.chessType == ChessType.Red) { //Red Chessman

                when(chessman.javaClass) {
                    JuChessman::class.java -> GameRes.Actor_Ju_Red_Normal
                    MaChessman::class.java -> GameRes.Actor_Ma_Red_Normal
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Red_Normal
                    ShiChessman::class.java -> GameRes.Actor_Shi_Red_Normal
                    KingChessman::class.java -> GameRes.Actor_King_Red_Normal
                    PaoChessman::class.java -> GameRes.Actor_Pao_Red_Normal
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Red_Normal
                    else -> null
                }
            } else {
                when(chessman.javaClass) { //Black Chessman
                    JuChessman::class.java -> GameRes.Actor_Ju_Black_Normal
                    MaChessman::class.java -> GameRes.Actor_Ma_Black_Normal
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Black_Normal
                    ShiChessman::class.java -> GameRes.Actor_Shi_Black_Normal
                    KingChessman::class.java -> GameRes.Actor_King_Black_Normal
                    PaoChessman::class.java -> GameRes.Actor_Pao_Black_Normal
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Black_Normal
                    else -> null
                }
            }
        }

        return null
    }

    /**
     * 根据具体棋子 Class 和 Type 查询 Picked 状态下加载的资源路径
     */
    fun queryResPathByPickedChessman(chessman : Chessman?) : String? {

        chessman?.let {
            return if (chessman.chessType == ChessType.Red) { //Red Chessman

                when(chessman.javaClass) {
                    JuChessman::class.java -> GameRes.Actor_Ju_Red_Picked
                    MaChessman::class.java -> GameRes.Actor_Ma_Red_Picked
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Red_Picked
                    ShiChessman::class.java -> GameRes.Actor_Shi_Red_Picked
                    KingChessman::class.java -> GameRes.Actor_King_Red_Picked
                    PaoChessman::class.java -> GameRes.Actor_Pao_Red_Picked
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Red_Picked
                    else -> null
                }
            } else {
                when(chessman.javaClass) { //Black Chessman
                    JuChessman::class.java -> GameRes.Actor_Ju_Black_Picked
                    MaChessman::class.java -> GameRes.Actor_Ma_Black_Picked
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Black_Picked
                    ShiChessman::class.java -> GameRes.Actor_Shi_Black_Picked
                    KingChessman::class.java -> GameRes.Actor_King_Black_Picked
                    PaoChessman::class.java -> GameRes.Actor_Pao_Black_Picked
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Black_Picked
                    else -> null
                }
            }
        }
        return null
    }


}