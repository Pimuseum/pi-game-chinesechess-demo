package com.pimuseum.game.chinesechess.model.companion

/**
 * 下棋落子结果
 */
enum class MoveResult {
    Success,//下棋成功
    DesIsSelf,//落点即是本身
    UnSupportChessRule,//不符合象棋游戏规则
    NoPickedChessMan,//无选取棋子
    GameOver,//游戏结束
}