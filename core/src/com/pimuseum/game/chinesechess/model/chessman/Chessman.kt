package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic

/**
 * Desc : Chessman
 * Author : Jiervs
 * Date : 2019/3/20
 */
abstract class Chessman(var chessType: ChessType, var position: Position) {

    /**
     * 不同种类棋子规则，即棋子自我约束
     */
    abstract fun chessmanRule(nextPosition: Position) : Boolean

    /**
     * 与棋盘上产生的联动效应，即棋盘及其他棋子约束
     */
    abstract fun chessboardRule(chessboardInfo : Array<Array<Chessman?>>, nextPosition: Position) : Boolean

    /**
     * 检测下棋可行性
     */
    fun checkOverallRules(chessboardInfo : Array<Array<Chessman?>>, nextPosition: Position) : Boolean {

        ChessLogic.isExistChessman(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@Chessman.chessType) return false//同色棋子不能被吃
        }

        return chessmanRule(nextPosition) && chessboardRule(ChessHelper.queryChessboardInfo(),nextPosition)
    }

    /**
     * 棋子名
     */
    abstract fun chessmanName() : String

    /**
     * 更新棋子自己的坐标信息
     */
    fun updateChessmanPosition(row : Int,column : Int) {
        position.row = row
        position.column = column
    }
}