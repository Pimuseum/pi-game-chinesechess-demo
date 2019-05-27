package com.pimuseum.game.chinesechess.model

import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.chessman.Chessman
import com.pimuseum.game.chinesechess.model.chessman.JuChessman
import com.pimuseum.game.chinesechess.model.chessman.KingChessman
import com.pimuseum.game.chinesechess.model.chessman.MaChessman
import com.pimuseum.game.chinesechess.model.chessman.PaoChessman
import com.pimuseum.game.chinesechess.model.chessman.ShiChessman
import com.pimuseum.game.chinesechess.model.chessman.XiangChessman
import com.pimuseum.game.chinesechess.model.companion.*
import com.pimuseum.game.chinesechess.model.tools.ChessTools
import com.pimuseum.game.chinesechess.model.observer.OperateObserver

/**
 * Desc : ChessHelper
 * Author : Jiervs
 * Date : 2019/3/20
 */
object ChessHelper {

    //十行九列一共90个坐标点 ，这里为 10 * 11 表示 左下角的第一个点并非从 (0,0) 开始，而是从(1,1) 开始
    const val ColumnCapacity : Int = 10
    const val RowCapacity : Int = 11

    //棋盘上的二维坐标集信息,对应的索引取值是空值或者是棋子对象
    private var chessboardInfo : Array<Array<Chessman?>>
            = Array(RowCapacity ){Array<Chessman?>(ColumnCapacity) { null } }

    //己方视角下操作者 Type
    var myRoleType = ChessType.Red

    //当前哪方回合
    private var turnFlag = ChessType.Red

    //当前选择棋子
    private var pickedChessman : Chessman? = null

    //当前操作状态
    var operationStatus : OperationStatus = OperationStatus.ChessFreedom

    //棋盘操作监听
    var observer : OperateObserver? = null

    //获取当前棋盘坐标集信息
    fun queryChessboardInfo() : Array<Array<Chessman?>> {
        return chessboardInfo
    }

    /**
     * 提起棋子
     */
    fun pickChessman(chessPosition : Position) : Boolean{

        ChessTools.isExistChessmanByPosition(
                queryChessboardInfo(),chessPosition)?.let { chessman->
            if (chessman.chessType == turnFlag) {
                pickedChessman = chessman
                operationStatus = OperationStatus.ChessPicked
                return true
            }
        }
        return false
    }

    /**
     * 放下棋子
     */
    fun dropChessman() {
        pickedChessman = null
        operationStatus = OperationStatus.ChessFreedom
    }

    /**
     * 查询当前 picked 棋子
     */
    fun queryPickedChessman() : Chessman? {
        return if (operationStatus == OperationStatus.ChessPicked) {
            pickedChessman
        } else {
            null
        }
    }

    /**
     * 下棋落子
     */
    fun moveChessman(nextPosition : Position) : MoveResult {

        pickedChessman?.let { pickedChessman ->

            if (pickedChessman.position.column == nextPosition.column && pickedChessman.position.row == nextPosition.row) {
                observer?.onDropBack(pickedChessman)
                return@moveChessman MoveResult.DesIsSelf
            }

            //检测是否符合下棋规则,包括棋子约束和棋盘约束
            if (pickedChessman.chessmanRule(nextPosition)
                && pickedChessman.chessboardRule(queryChessboardInfo(),nextPosition)) {

                //删掉落点处棋子
                ChessTools.isExistChessmanByPosition(queryChessboardInfo(),nextPosition)?.let { removeChessman->
                    queryChessboardInfo()[removeChessman.position.row][removeChessman.position.column] = null
                    observer?.onRemoveChessman(removeChessman)
                    if (removeChessman is KingChessman) return@moveChessman MoveResult.GameOver
                }

                /**
                 * 主要逻辑步骤：
                 */
                //置空旧坐标对 picked chess的引用
                queryChessboardInfo()[pickedChessman.position.row][pickedChessman.position.column] = null
                observer?.onMoveChessman(pickedChessman.position.row,pickedChessman.position.column)

                //更新被选择棋子的坐标信息
                pickedChessman.updateChessmanPosition(nextPosition.row,nextPosition.column)

                //新的落点处坐标数组中对应索引指向选择棋子对象
                queryChessboardInfo()[nextPosition.row][nextPosition.column] = pickedChessman

                //切换回合
                turnFlag = if (turnFlag == ChessType.Red) ChessType.Black
                            else ChessType.Red

                return@moveChessman MoveResult.Success
            }
            return@moveChessman MoveResult.UnSupportChessRule
        }
        return MoveResult.NoPickedChessMan
    }

    /**
     * 根据Position 判断棋子是否存在
     */
    fun isExistChessmanByPosition(position: Position) : Chessman? {
        chessboardInfo[position.row][position.column]?.let { chessman ->
            return chessman
        }
        return null
    }

    /**
     * 载入棋子
     */
    fun loadChessmen() {

        dropChessman()
        turnFlag = ChessType.Red

        //清空棋盘坐标上的棋子
        for(row in 1 until RowCapacity) {
            for (column in 1 until ColumnCapacity) {
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
        chessboardInfo[3][2] = PaoChessman(ChessType.Red, Position(3, 2))
        chessboardInfo[3][8] = PaoChessman(ChessType.Red, Position(3, 8))
        //兵
        chessboardInfo[4][1] = PawnChessman(ChessType.Red, Position(4, 1))
        chessboardInfo[4][3] = PawnChessman(ChessType.Red, Position(4, 3))
        chessboardInfo[4][5] = PawnChessman(ChessType.Red, Position(4, 5))
        chessboardInfo[4][7] = PawnChessman(ChessType.Red, Position(4, 7))
        chessboardInfo[4][9] = PawnChessman(ChessType.Red, Position(4, 9))

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
        chessboardInfo[8][2] = PaoChessman(ChessType.Black, Position(8, 2))
        chessboardInfo[8][8] = PaoChessman(ChessType.Black, Position(8, 8))
        //卒
        chessboardInfo[7][1] = PawnChessman(ChessType.Black, Position(7, 1))
        chessboardInfo[7][3] = PawnChessman(ChessType.Black, Position(7, 3))
        chessboardInfo[7][5] = PawnChessman(ChessType.Black, Position(7, 5))
        chessboardInfo[7][7] = PawnChessman(ChessType.Black, Position(7, 7))
        chessboardInfo[7][9] = PawnChessman(ChessType.Black, Position(7, 9))

        observer?.onLoadChessmen()
    }
}