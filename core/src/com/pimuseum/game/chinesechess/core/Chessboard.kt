package com.pimuseum.game.chinesechess.core

import com.pimuseum.game.chinesechess.core.chessman.*
import com.pimuseum.game.chinesechess.core.companion.ChessType
import com.pimuseum.game.chinesechess.core.companion.Position

/**
 * Desc : Chessboard
 * Author : Jiervs
 * Date : 2019/3/20
 */
class Chessboard {

    companion object {
        //十行九列一共90个坐标点
        private const val ColumnCapacity : Int = 9
        private const val RowCapacity : Int = 10
    }

    //棋盘上的二维坐标集信息,对应的索引取值是空值或者是棋子对象
    private var chessboardInfo : Array<Array<Chessman?>>
            = Array(RowCapacity){Array<Chessman?>(ColumnCapacity) { null } }

    //获取当前棋盘坐标集信息
    fun queryChessboardInfo() : Array<Array<Chessman?>> {
        return chessboardInfo
    }

    //载入棋子
    fun loadChessman() {

        //清空棋盘坐标上的棋子
        for(row in 1..RowCapacity) {
            for (column in 1..ColumnCapacity) {
                chessboardInfo[row][column] = null
            }
        }

        //红车
        chessboardInfo[1][1] = JuChessman(ChessType.Red, Position(1, 1))
        chessboardInfo[1][9] = JuChessman(ChessType.Red, Position(1, 9))
        //红马
        chessboardInfo[1][2] = MaChessman(ChessType.Red, Position(1, 2))
        chessboardInfo[1][8] = MaChessman(ChessType.Red, Position(1, 8))
        //红相
        chessboardInfo[1][3] = XiangChessman(ChessType.Red, Position(1, 3))
        chessboardInfo[1][7] = XiangChessman(ChessType.Red, Position(1, 7))
        //红士
        chessboardInfo[1][4] = ShiChessman(ChessType.Red, Position(1, 4))
        chessboardInfo[1][6] = ShiChessman(ChessType.Red, Position(1, 6))
        //帅
        chessboardInfo[1][5] = KingChessman(ChessType.Red, Position(1, 5))
        //红炮
        chessboardInfo[2][2] = PaoChessman(ChessType.Red, Position(2, 2))
        chessboardInfo[2][8] = PaoChessman(ChessType.Red, Position(2, 8))
        //兵
        chessboardInfo[4][1] = PawnsChessman(ChessType.Red, Position(4, 1))
        chessboardInfo[4][3] = PawnsChessman(ChessType.Red, Position(4, 3))
        chessboardInfo[4][5] = PawnsChessman(ChessType.Red, Position(4, 5))
        chessboardInfo[4][7] = PawnsChessman(ChessType.Red, Position(4, 7))
        chessboardInfo[4][9] = PawnsChessman(ChessType.Red, Position(4, 9))

        //黑车
        chessboardInfo[10][1] = JuChessman(ChessType.Black, Position(10, 1))
        chessboardInfo[10][9] = JuChessman(ChessType.Black, Position(10, 9))
        //黑马
        chessboardInfo[10][2] = MaChessman(ChessType.Black, Position(10, 2))
        chessboardInfo[10][8] = MaChessman(ChessType.Black, Position(10, 8))
        //黑象
        chessboardInfo[10][3] = XiangChessman(ChessType.Black, Position(10, 3))
        chessboardInfo[10][7] = XiangChessman(ChessType.Black, Position(10, 7))
        //黑仕
        chessboardInfo[10][4] = ShiChessman(ChessType.Black, Position(10, 4))
        chessboardInfo[10][6] = ShiChessman(ChessType.Black, Position(10, 6))
        //将
        chessboardInfo[10][5] = KingChessman(ChessType.Black, Position(10, 5))
        //黑炮
        chessboardInfo[9][2] = PaoChessman(ChessType.Black, Position(9, 2))
        chessboardInfo[9][8] = PaoChessman(ChessType.Black, Position(9, 8))
        //卒
        chessboardInfo[7][1] = PawnsChessman(ChessType.Black, Position(7, 1))
        chessboardInfo[7][3] = PawnsChessman(ChessType.Black, Position(7, 3))
        chessboardInfo[7][5] = PawnsChessman(ChessType.Black, Position(7, 5))
        chessboardInfo[7][7] = PawnsChessman(ChessType.Black, Position(7, 7))
        chessboardInfo[7][9] = PawnsChessman(ChessType.Black, Position(7, 9))

    }
}