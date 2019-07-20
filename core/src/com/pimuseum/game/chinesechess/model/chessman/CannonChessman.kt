package com.pimuseum.game.chinesechess.model.chessman

import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.logic.ChessLogic

/**
 * Desc : CannonChessman(炮)
 * Author : Jiervs
 * Date : 2019/3/20
 */
class CannonChessman(chessType: ChessType, position: Position) : Chessman(chessType, position) {

    override fun chessmanRule(nextPosition: Position): Boolean {

        //炮列行直走
        return ((nextPosition.column == position.column && nextPosition.row != position.row)
                || (nextPosition.column != position.column && nextPosition.row == position.row))
    }

    override fun chessboardRule(chessboardInfo: Array<Array<Chessman?>>, nextPosition: Position): Boolean {

        ChessLogic.isExistChessman(chessboardInfo,nextPosition)?.let { chessman->
            if (chessman.chessType == this@CannonChessman.chessType) return false//同色棋子不能被吃

            //落点存在对方棋子时候，需隔山打炮
            return ChessLogic.numberBetween2Positions(chessboardInfo,this@CannonChessman.position,nextPosition) == 1
        }

        //落点不存在对方棋子时候，炮行列皆行
        return ChessLogic.numberBetween2Positions(chessboardInfo,this@CannonChessman.position,nextPosition) == 0

    }

    override fun chessmanName(): String {
        return "炮"
    }
}