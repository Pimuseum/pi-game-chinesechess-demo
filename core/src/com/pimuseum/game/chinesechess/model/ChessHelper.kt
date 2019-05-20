package com.pimuseum.game.chinesechess.model

import com.badlogic.gdx.Gdx
import com.pimuseum.game.chinesechess.core.chessman.*
import com.pimuseum.game.chinesechess.engine.constant.Res
import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.chessman.Chessman
import com.pimuseum.game.chinesechess.model.chessman.JuChessman
import com.pimuseum.game.chinesechess.model.chessman.KingChessman
import com.pimuseum.game.chinesechess.model.chessman.MaChessman
import com.pimuseum.game.chinesechess.model.chessman.PaoChessman
import com.pimuseum.game.chinesechess.model.chessman.ShiChessman
import com.pimuseum.game.chinesechess.model.chessman.XiangChessman
import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.OperationStatus
import com.pimuseum.game.chinesechess.model.companion.Position
import com.pimuseum.game.chinesechess.model.tools.ChessmanTools


/**
 * Desc : ChessHelper
 * Author : Jiervs
 * Date : 2019/3/20
 */
object ChessHelper {

    //十行九列一共90个坐标点
    const val ColumnCapacity : Int = 9
    const val RowCapacity : Int = 10

    //棋盘上的二维坐标集信息,对应的索引取值是空值或者是棋子对象
    private var chessboardInfo : Array<Array<Chessman?>>
            = Array(RowCapacity + 1){Array<Chessman?>(ColumnCapacity + 1) { null } }

    //获取当前棋盘坐标集信息
    fun queryChessboardInfo() : Array<Array<Chessman?>> {
        return chessboardInfo
    }

    //当前哪方回合
    private var turnFlag = ChessType.Red

    //当前选择棋子
    private var pickedChessman : Chessman? = null

    //当前操作状态
    var operationStatus : OperationStatus = OperationStatus.ChessFreedom

    /**
     * 提起棋子
     */
    fun pickChessman(chessPosition : Position) {

        ChessmanTools.isExistChessmanByPosition(
                queryChessboardInfo(),chessPosition)?.let { chessman->
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
                && pickedChessman.chessboardRule(queryChessboardInfo(),nextPosition)) {

                //删掉落点处棋子
                ChessmanTools.isExistChessmanByPosition(
                        queryChessboardInfo(),nextPosition)?.let { removeChessman->
                    queryChessboardInfo()[removeChessman.position.row][removeChessman.position.column] = null
                }

                //下棋落子，逻辑步骤：

                //被选择棋子原来坐标数组中的棋子对象的引用清空
                queryChessboardInfo()[pickedChessman.position.row][pickedChessman.position.column] = null
                //更新被选择棋子的坐标信息
                pickedChessman.updateChessmanPosition(nextPosition.row,nextPosition.column)
                //新的落点处坐标数组中对应索引指向选择棋子对象
                queryChessboardInfo()[nextPosition.row][nextPosition.column] = pickedChessman

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

    /**
     * 载入棋子
     */
    fun loadChessman() {

        //清空棋盘坐标上的棋子
        for(row in 1..RowCapacity) {
            for (column in 1..ColumnCapacity) {
                Gdx.app.log(Res.TAG, "row: $row * column:$column")
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

    }
}