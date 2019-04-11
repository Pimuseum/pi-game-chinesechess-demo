package com.pimuseum.game.chinesechess.core

import com.pimuseum.game.chinesechess.core.chessman.Chessman
import com.pimuseum.game.chinesechess.core.companion.ChessType
import com.pimuseum.game.chinesechess.core.companion.OperationStatus
import com.pimuseum.game.chinesechess.core.companion.Position
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
    fun pickChessman(chessPosition : Position) {

        ChessmanTools.isExistChessmanByPosition(
            chessboard.queryChessboardInfo(),chessPosition)?.let { chessman->
            if (chessman.chessType == turnFlag) {
                pickedChessman = chessman
                operationStatus = OperationStatus.ChessPicked
            }
        }
    }

    /**
     * 取消提起棋子
     */
    private fun dropChessman() {
        pickedChessman = null
        operationStatus = OperationStatus.ChessFreedom
    }

    /**
     * 下棋
     */
    fun moveChessman(nextPosition : Position) : Boolean {

        pickedChessman?.let { pickedChessman ->

            if (pickedChessman.position.column == nextPosition.column && pickedChessman.position.row == nextPosition.row) {
                dropChessman()
                return@moveChessman false
            }

            //检测是否符合下棋规则,包括棋子约束和棋盘约束
            if (pickedChessman.chessmanRule(nextPosition)
                && pickedChessman.chessboardRule(chessboard.queryChessboardInfo(),nextPosition)) {

                //删掉落点处棋子
                ChessmanTools.isExistChessmanByPosition(
                    chessboard.queryChessboardInfo(),nextPosition)?.let { removeChessman->
                    chessboard.queryChessboardInfo()[removeChessman.position.row][removeChessman.position.column] = null
                }

                //下棋落子，逻辑步骤：

                //被选择棋子原来坐标数组中的棋子对象的引用清空
                chessboard.queryChessboardInfo()[pickedChessman.position.row][pickedChessman.position.column] = null
                //更新被选择棋子的坐标信息
                pickedChessman.updateChessmanPosition(nextPosition.row,nextPosition.column)
                //新的落点处坐标数组中对应索引指向选择棋子对象
                chessboard.queryChessboardInfo()[nextPosition.row][nextPosition.column] = pickedChessman

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
        return false
    }
}