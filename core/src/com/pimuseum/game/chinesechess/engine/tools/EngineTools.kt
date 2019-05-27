package com.pimuseum.game.chinesechess.engine.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.pimuseum.game.chinesechess.engine.actor.ChessmanActor
import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.companion.ChessType
import com.pimuseum.game.chinesechess.model.companion.Position

object EngineTools {

    /**
     * 根据触摸点转换成象棋逻辑坐标
     */
    fun stageCovert2Position(vector : Vector2,
                                    originLocationX : Float , originLocationY: Float,
                                    chessboardUnitWidth : Float,chessboardUnitHeight : Float) : Position?{

        //触摸点击是否在棋盘内部
        return if (vector.x >= originLocationX && vector.y >= originLocationY &&
                vector.x <= originLocationX + 10 * chessboardUnitWidth && vector.y <= originLocationY + 11 * chessboardUnitHeight) {

            val row : Int = Math.round((vector.y - originLocationY) / chessboardUnitHeight)
            val column : Int = Math.round((vector.x - originLocationX) / chessboardUnitWidth)
            if (ChessHelper.myRoleType == ChessType.Red) {
                Position(row,column)
            } else {
                Position(ChessHelper.RowCapacity - row,ChessHelper.ColumnCapacity - column)
            }
        } else {
            null
        }
    }

    /**
     * 给指定的 ChessmanActor 替换纹理资源
     */
    fun replaceActorTexture(chessmanActor: ChessmanActor?,resPath : String?) {

        //查找棋子 picked 资源资源
        resPath?.let { it->
                val chessmanTexture = Texture(Gdx.files.internal(it))
            chessmanActor?.let { actor->
                actor.region = TextureRegion(chessmanTexture)
            } }
    }
}