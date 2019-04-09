package com.pimuseum.game.chinesechess.core


/**
 * Desc : Chessboard
 * Author : Jiervs
 * Date : 2019/3/20
 */
abstract class Chessman(var chessType: ChessType, var position: Position) {

    fun moveTo(column : Int , row : Int) {
        position.column = column
        position.row = row
    }

    /**
     * 不同种类棋子规则，即棋子自我约束
     */
    abstract fun chessmanRule(nextPosition: Position) : Boolean

    /**
     * 与棋盘上产生的联动效应，即棋盘及其他棋子约束
     */
    abstract fun chessboardRule(chessmanList : ArrayList<Chessman>,nextPosition: Position) : Boolean

    /**
     * 棋子名
     */
    abstract fun chessmanName() : String
}