package com.pimuseum.game.chinesechess.core

import com.pimuseum.game.chinesechess.core.tools.ChessmanTools


/**
 * Desc : ChessHelper
 * Author : Jiervs
 * Date : 2019/3/20
 */
object ChessHelper {

    //当前哪方回合
    @Volatile private var turnFlag = ChessType.Red

    //当前选择棋子
    private var pickedChessman : Chessman? = null

    //当前操作状态
    @Volatile var operationStatus : OperationStatus = OperationStatus.ChessFreedom

    private lateinit var chessboard : Chessboard

    fun bindChessboard(chessboard : Chessboard) {
        this.chessboard = chessboard
    }

    /**
     * 提起棋子
     */
    @Synchronized fun pickChessman(chessPosition : Position) {

        ChessmanTools.isExistChessmanByPosition(
            chessboard.getCurrentChessmanList(),chessPosition)?.let { chessman->
            if (chessman.chessType == turnFlag) {
                pickedChessman = chessman
                operationStatus = OperationStatus.ChessPicked
            }
        }
    }

    /**
     * 取消提起棋子
     */
    @Synchronized fun dropChessman() {
        pickedChessman = null
        operationStatus = OperationStatus.ChessFreedom
    }

    /**
     * 下棋
     */
    @Synchronized fun moveChessman(nextPosition : Position) : Boolean {

        pickedChessman?.let { pickedChessman ->

            if (pickedChessman.position.column == nextPosition.column && pickedChessman.position.row == nextPosition.row) {
                dropChessman()
                return@moveChessman false
            }

            //检测是否符合下棋规则
            if (pickedChessman.chessmanRule(nextPosition)
                && pickedChessman.chessboardRule(chessboard.getCurrentChessmanList(),nextPosition)) {

                //删掉落点处棋子
                ChessmanTools.isExistChessmanByPosition(
                    chessboard.getCurrentChessmanList(),nextPosition)?.let { removeChessman->
                    chessboard.removeChessman(removeChessman)
                }

                //下棋
                pickedChessman.moveTo(nextPosition.column,nextPosition.row)
                dropChessman()

                //切换回合
                turnFlag = if (turnFlag == ChessType.Red) {
                    ChessType.Black
                } else {
                    ChessType.Red
                }

                return true
            }

            return false
        }
    }
}