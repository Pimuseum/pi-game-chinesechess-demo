package com.pimuseum.game.chinesechess.core

import com.pimuseum.game.chinesechess.core.chessman.*
import com.pimuseum.game.chinesechess.core.support.ChessType
import com.pimuseum.game.chinesechess.core.support.Position

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

//    private var chessmanList : ArrayList<Chessman?> = ArrayList(90)
//
//
//    //获取当前棋盘上的棋子
//    fun getCurrentChessmanList() : ArrayList<Chessman?> {
//        return chessmanList
//    }

    //棋盘上的二维坐标集信息,对应的索引取值是空值或者是棋子对象
    private var chessboardInfo : Array<Array<Chessman?>>
            = Array(RowCapacity){Array<Chessman?>(ColumnCapacity) { null } }

    //获取当前棋盘坐标集信息
    fun queryChessboardInfo() : Array<Array<Chessman?>> {
        return chessboardInfo
    }

    //载入棋子
    fun loadChessman() {

//        chessmanList.clear()
//
//        //红车
//        chessmanList.add(JuChessman(ChessType.Red, Position(1, 1)))
//        chessmanList.add(JuChessman(ChessType.Red, Position(1, 9)))
//        //红马
//        chessmanList.add(MaChessman(ChessType.Red, Position(1, 2)))
//        chessmanList.add(MaChessman(ChessType.Red, Position(1, 8)))
//        //红相
//        chessmanList.add(XiangChessman(ChessType.Red, Position(1, 3)))
//        chessmanList.add(XiangChessman(ChessType.Red, Position(1, 7)))
//        //红士
//        chessmanList.add(ShiChessman(ChessType.Red, Position(1, 4)))
//        chessmanList.add(ShiChessman(ChessType.Red, Position(1, 6)))
//        //帅
//        chessmanList.add(KingChessman(ChessType.Red, Position(1, 5)))
//        //红炮
//        chessmanList.add(PaoChessman(ChessType.Red, Position(2, 2)))
//        chessmanList.add(PaoChessman(ChessType.Red, Position(2, 8)))
//        //兵
//        chessmanList.add(PawnsChessman(ChessType.Red, Position(4, 1)))
//        chessmanList.add(PawnsChessman(ChessType.Red, Position(4, 3)))
//        chessmanList.add(PawnsChessman(ChessType.Red, Position(4, 5)))
//        chessmanList.add(PawnsChessman(ChessType.Red, Position(4, 7)))
//        chessmanList.add(PawnsChessman(ChessType.Red, Position(4, 9)))
//
//        //黑车
//        chessmanList.add(JuChessman(ChessType.Black, Position(10, 1)))
//        chessmanList.add(JuChessman(ChessType.Black, Position(10, 9)))
//        //黑马
//        chessmanList.add(MaChessman(ChessType.Black, Position(10, 2)))
//        chessmanList.add(MaChessman(ChessType.Black, Position(10, 8)))
//        //黑象
//        chessmanList.add(XiangChessman(ChessType.Black, Position(10, 3)))
//        chessmanList.add(XiangChessman(ChessType.Black, Position(10, 7)))
//        //黑仕
//        chessmanList.add(ShiChessman(ChessType.Black, Position(10, 4)))
//        chessmanList.add(ShiChessman(ChessType.Black, Position(10, 6)))
//        //将
//        chessmanList.add(KingChessman(ChessType.Black, Position(10, 5)))
//        //黑炮
//        chessmanList.add(PaoChessman(ChessType.Black, Position(9, 2)))
//        chessmanList.add(PaoChessman(ChessType.Black, Position(9, 8)))
//
//        //卒
//        chessmanList.add(PawnsChessman(ChessType.Black, Position(7, 1)))
//        chessmanList.add(PawnsChessman(ChessType.Black, Position(7, 3)))
//        chessmanList.add(PawnsChessman(ChessType.Black, Position(7, 5)))
//        chessmanList.add(PawnsChessman(ChessType.Black, Position(7, 7)))
//        chessmanList.add(PawnsChessman(ChessType.Black, Position(7, 9)))
    }

    //棋子被吃
    fun removeChessman (chessman : Chessman) {
        //chessmanList.remove(chessman)
    }

}