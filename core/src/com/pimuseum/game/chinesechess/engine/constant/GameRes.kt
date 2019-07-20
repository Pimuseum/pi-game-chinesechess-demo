package com.pimuseum.game.chinesechess.engine.constant

import com.pimuseum.game.chinesechess.model.chessman.*
import com.pimuseum.game.chinesechess.model.companion.ChessType

object GameRes {

    /**
     * 根据具体棋子 Class 和 Type 查询 Normal 状态下加载的资源路径
     */
    fun queryResPathByNormalChessman(chessman : Chessman?) : String? {

        chessman?.let {

            return if (chessman.chessType == ChessType.Red) { //Red Chessman

                when(chessman.javaClass) {
                    JuChessman::class.java -> GameRes.Actor_Ju_Red_Normal
                    MaChessman::class.java -> GameRes.Actor_Ma_Red_Normal
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Red_Normal
                    ShiChessman::class.java -> GameRes.Actor_Shi_Red_Normal
                    KingChessman::class.java -> GameRes.Actor_King_Red_Normal
                    PaoChessman::class.java -> GameRes.Actor_Pao_Red_Normal
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Red_Normal
                    else -> null
                }
            } else {
                when(chessman.javaClass) { //Black Chessman
                    JuChessman::class.java -> GameRes.Actor_Ju_Black_Normal
                    MaChessman::class.java -> GameRes.Actor_Ma_Black_Normal
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Black_Normal
                    ShiChessman::class.java -> GameRes.Actor_Shi_Black_Normal
                    KingChessman::class.java -> GameRes.Actor_King_Black_Normal
                    PaoChessman::class.java -> GameRes.Actor_Pao_Black_Normal
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Black_Normal
                    else -> null
                }
            }
        }

        return null
    }

    /**
     * 根据具体棋子 Class 和 Type 查询 Picked 状态下加载的资源路径
     */
    fun queryResPathByPickedChessman(chessman : Chessman?) : String? {

        chessman?.let {
            return if (chessman.chessType == ChessType.Red) { //Red Chessman

                when(chessman.javaClass) {
                    JuChessman::class.java -> GameRes.Actor_Ju_Red_Picked
                    MaChessman::class.java -> GameRes.Actor_Ma_Red_Picked
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Red_Picked
                    ShiChessman::class.java -> GameRes.Actor_Shi_Red_Picked
                    KingChessman::class.java -> GameRes.Actor_King_Red_Picked
                    PaoChessman::class.java -> GameRes.Actor_Pao_Red_Picked
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Red_Picked
                    else -> null
                }
            } else {
                when(chessman.javaClass) { //Black Chessman
                    JuChessman::class.java -> GameRes.Actor_Ju_Black_Picked
                    MaChessman::class.java -> GameRes.Actor_Ma_Black_Picked
                    XiangChessman::class.java -> GameRes.Actor_Xiang_Black_Picked
                    ShiChessman::class.java -> GameRes.Actor_Shi_Black_Picked
                    KingChessman::class.java -> GameRes.Actor_King_Black_Picked
                    PaoChessman::class.java -> GameRes.Actor_Pao_Black_Picked
                    PawnChessman::class.java -> GameRes.Actor_Pawn_Black_Picked
                    else -> null
                }
            }
        }
        return null
    }

    /*********************************        constant resource      ********************************/

    const val RealChessboardWidth : Float = 510F
    const val RealChessboardHeight : Float = 578F
    const val RealHorizontalMargin : Float = 33F
    const val RealVerticalMargin : Float = 40F


    /*********************************        sound resource      ********************************/

    const val Sound_Drop = "sound/drop.mp3"
    const val Sound_Pick = "sound/pick.mp3"

    /*********************************        actor resource      ********************************/

    const val Actor_Game_Bg = "actor/chess_versus_game_bg.png"
    const val Actor_Chessboard = "actor/chessboard.png"
    const val Actor_Origin_Trace = "actor/origin_des_trace.png"
    const val Actor_Des_Trace = "actor/origin_des_trace.png"

    const val Actor_Ju_Red_Normal = "actor/chessman_rj.png"
    const val Actor_Ma_Red_Normal = "actor/chessman_rm.png"
    const val Actor_Xiang_Red_Normal = "actor/chessman_rx.png"
    const val Actor_Shi_Red_Normal = "actor/chessman_rs.png"
    const val Actor_King_Red_Normal = "actor/chessman_rk.png"
    const val Actor_Pao_Red_Normal = "actor/chessman_rp.png"
    const val Actor_Pawn_Red_Normal = "actor/chessman_rpawn.png"

    const val Actor_Ju_Black_Normal = "actor/chessman_bj.png"
    const val Actor_Ma_Black_Normal = "actor/chessman_bm.png"
    const val Actor_Xiang_Black_Normal = "actor/chessman_bx.png"
    const val Actor_Shi_Black_Normal = "actor/chessman_bs.png"
    const val Actor_King_Black_Normal = "actor/chessman_bk.png"
    const val Actor_Pao_Black_Normal = "actor/chessman_bp.png"
    const val Actor_Pawn_Black_Normal = "actor/chessman_bpawn.png"

    const val Actor_Ju_Red_Picked = "actor/chessman_rjp.png"
    const val Actor_Ma_Red_Picked = "actor/chessman_rmp.png"
    const val Actor_Xiang_Red_Picked = "actor/chessman_rxp.png"
    const val Actor_Shi_Red_Picked = "actor/chessman_rsp.png"
    const val Actor_King_Red_Picked = "actor/chessman_rkp.png"
    const val Actor_Pao_Red_Picked = "actor/chessman_rpp.png"
    const val Actor_Pawn_Red_Picked = "actor/chessman_rpawnp.png"

    const val Actor_Ju_Black_Picked = "actor/chessman_bjp.png"
    const val Actor_Ma_Black_Picked = "actor/chessman_bmp.png"
    const val Actor_Xiang_Black_Picked = "actor/chessman_bxp.png"
    const val Actor_Shi_Black_Picked = "actor/chessman_bsp.png"
    const val Actor_King_Black_Picked = "actor/chessman_bkp.png"
    const val Actor_Pao_Black_Picked = "actor/chessman_bpp.png"
    const val Actor_Pawn_Black_Picked = "actor/chessman_bpawnp.png"

}

