package com.pimuseum.game.chinesechess.engine.stage

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.pimuseum.game.chinesechess.engine.actor.ImageActor
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.pimuseum.game.chinesechess.model.ChessHelper
import com.pimuseum.game.chinesechess.model.tools.ChessTools
import com.pimuseum.game.chinesechess.engine.actor.ChessBoardActor
import com.pimuseum.game.chinesechess.engine.actor.ChessmanActor
import com.pimuseum.game.chinesechess.engine.constant.LogTag
import com.pimuseum.game.chinesechess.engine.tools.EngineTools
import com.pimuseum.game.chinesechess.model.companion.MoveResult
import com.pimuseum.game.chinesechess.model.companion.OperationStatus

/**
 * Desc : ChessVersusStage
 * Author : Jiervs
 * Date : 2019/5/16
 */
class ChessVersusStage(viewport: Viewport) : Stage(viewport) {

    private var chessboardWidth : Float = 0F
    private var chessboardHeight : Float = 0F

    private var chessboardLogicWidth : Float = 0F
    private var chessboardLogicHeight : Float = 0F

    private var chessboardUnitWidth : Float = 0F
    private var chessboardUnitHeight : Float = 0F

    private var chessmanSize : Float = 0F

    private var originLocationX = 0F
    private var originLocationY = 0F

    /**
     * Actors
     */
    private lateinit var bgActor : ImageActor
    private lateinit var oriActor : ImageActor
    private lateinit var desActor : ImageActor
    private lateinit var chessboardActor : ChessBoardActor
    private var chessmanActors : Array<Array<ChessmanActor?>>
            = Array(ChessHelper.RowCapacity + 1){Array<ChessmanActor?>(ChessHelper.ColumnCapacity + 1) { null}}

    /**
     * Sounds
     */
    private lateinit var pickSound : Sound
    private lateinit var dropSound : Sound
    private lateinit var captureSound : Sound

    /**
     * Music
     */
    private lateinit var bgm : Music

    init {
        //chessboard bg size
        chessboardWidth = width * (9F / 10F)
        chessboardHeight = width * ( 9F / 10F) * (578F / 510F)

        //chessboard logic size
        chessboardLogicWidth = chessboardWidth * ((510F - 2 * 33F) / 510F)
        chessboardLogicHeight = chessboardHeight * ((578F - 2 * 40F) / 578F)

        //chessboard unit size (not square)
        chessboardUnitWidth = chessboardLogicWidth / 8
        chessboardUnitHeight = chessboardLogicHeight / 9

        chessmanSize = chessboardUnitWidth * 9 / 10

        //ensure logic original point location
        originLocationX = (width / 2F) - (5F * chessboardUnitWidth)
        originLocationY = (height / 2F) - (5.5F * chessboardUnitHeight)

        initActors()
    }

    /**
     * Init Actors
     */
    private fun initActors(){
        //create background actor
        val bgTexture = Texture(Gdx.files.internal("actor/bg.png"))
        bgActor = ImageActor(TextureRegion(bgTexture))
        bgActor.setCenter(width / 2, height / 2)
        addActor(bgActor)

        //create chessboard actor
        val chessboardTexture = Texture(Gdx.files.internal("actor/chessboard.png"))
        chessboardActor = ChessBoardActor(TextureRegion(chessboardTexture))
        chessboardActor.setSize(chessboardWidth,chessboardHeight)
        chessboardActor.setCenter(width / 2, height / 2)
        addActor(chessboardActor)

        //create origin actor
        val originTexture = Texture(Gdx.files.internal("actor/origin_des_trace.png"))
        oriActor = ImageActor(TextureRegion(originTexture))
        oriActor.setSize(chessboardUnitWidth,chessboardUnitWidth)
        oriActor.setCenter(2 * width , 2 * height) //初始化时候先放在 Stage 外面
        oriActor.isVisible = false
        addActor(oriActor)

        //create destination actor
        val desTexture = Texture(Gdx.files.internal("actor/origin_des_trace.png"))
        desActor = ImageActor(TextureRegion(desTexture))
        desActor.setSize(chessboardUnitWidth,chessboardUnitWidth)
        desActor.setCenter(2 * width , 2 * height) //初始化时候先放在 Stage 外面
        desActor.isVisible = false
        addActor(desActor)

        //create chessmen actors
        initChessmanActors()

        //create sound
        pickSound = Gdx.audio.newSound(Gdx.files.internal("sound/pick.mp3"))
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sound/drop.mp3"))

        //create bgm
//      bgm = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm.mp3"))
//      bgm.volume /= 4
//      bgm.play()
    }

    /**
     * Create Chessman Actors
     */
    private fun initChessmanActors() {
        ChessHelper.loadChessman()
        val chessboardInfo = ChessHelper.queryChessboardInfo()

        for(row in 1..ChessHelper.RowCapacity) {
            for (column in 1..ChessHelper.ColumnCapacity) {

                chessboardInfo[row][column]?.let { chessman ->
                    //create chessman actor

                    ChessTools.queryResPathByNormalChessman(chessman)?.let { resPath->

                        val chessmanTexture = Texture(Gdx.files.internal(resPath))
                        val chessmanActor = ChessmanActor(TextureRegion(chessmanTexture),chessman)
                        chessmanActor.setSize(chessmanSize,chessmanSize)
                        chessmanActor.setCenter(originLocationX + (chessman.position.column * chessboardUnitWidth),
                                originLocationY + (chessman.position.row * chessboardUnitHeight))

                        addActor(chessmanActor)
                        chessmanActors[chessman.position.row][chessman.position.column] = chessmanActor

                        Gdx.app.log(LogTag.ChessLog, "${chessman.javaClass.simpleName}: ${chessman.position.row} *" +
                                " ${chessman.position.column}")
                    }
                }
            }
        }
    }

    /**
     * 释放资源
     */
    override fun dispose() {
        super.dispose()
        pickSound.dispose()
        dropSound.dispose()
        bgm.dispose()
    }

    /**
     * 处理游戏点击操作
     */
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {

        val stageVector : Vector2 = screenToStageCoordinates(Vector2(screenX.toFloat(), screenY.toFloat()))
        playChess(stageVector)


//        Gdx.app.log("Jiervs", "viewportX: ${viewport.worldWidth} .....viewportY: ${viewport.worldHeight}  \r\n " +
//                "originLocationX :$originLocationX.....originLocationY :$originLocationY \r\n " +
//                "stageVectorX :${vector.x}.....stageVectorY :${vector.y}" )

        return super.touchDown(screenX, screenY, pointer, button)
    }

    /**
     * 触摸棋盘以后的游戏逻辑步骤：
     *
     * 1.先查询象棋的棋子处于被选择状态还是自由状态
     *
     * 2.根据 状态操作 freedom:选取  pick:下棋
     */
    private fun playChess(stageVector2: Vector2) {

        //触摸点转换为象棋坐标
        EngineTools.stageCovert2Position(stageVector2,originLocationX,originLocationY,chessboardUnitWidth,chessboardUnitHeight)?.let { position->

            Gdx.app.log(LogTag.OPLog, "operationStatus : ${ChessHelper.operationStatus}")

            if(ChessHelper.operationStatus == OperationStatus.ChessFreedom) {// 棋子自由状态

                    //触摸点下是否存在棋子
                    ChessHelper.isExistChessmanByPosition(position)?.let { chessman ->

                        //pick 棋子 ，actor 纹理改变成 picked 状态
                        if (ChessHelper.pickChessman(position)) {

                            pickSound.play()

                            //隐藏上一步下棋轨迹
                            oriActor.isVisible = false
                            desActor.isVisible = false

                            EngineTools.replaceActorTexture(chessmanActors[position.row][position.column],
                                    ChessTools.queryResPathByPickedChessman(chessman))

                            chessmanActors[position.row][position.column]?.setSize(chessboardUnitWidth,chessboardUnitWidth)
                        }
                    }

            } else { // 棋子已选择状态，落下下棋

                //在下棋之前，记录一下 pickChessman 旧坐标，因为在 数据模型 ChessHelper 执行 moveChessman 方法后，数据模型上的 pickChessman 坐标已经 改变
                var oldRow = 0
                var oldColumn = 0

                ChessHelper.queryPickedChessman()?.let {
                    oldRow = it.position.row
                    oldColumn = it.position.column
                }

                val result = ChessHelper.moveChessman(position)
                Gdx.app.log(LogTag.OPLog, "MoveResult : $result")

                when(result) {

                    MoveResult.Success -> {

                        //调整 Actor 位置
                        chessmanActors[position.row][position.column]?.let {
                            root.removeActor(chessmanActors[position.row][position.column])
                            chessmanActors[position.row][position.column] = null
                        }

                        chessmanActors[position.row][position.column] = chessmanActors[oldRow][oldColumn]
                        chessmanActors[oldRow][oldColumn] = null

                        //actor 纹理改变成 freedom 状态,drop 棋子
                        EngineTools.replaceActorTexture(chessmanActors[position.row][position.column],
                                ChessTools.queryResPathByNormalChessman(chessmanActors[position.row][position.column]?.chessman))

                        chessmanActors[position.row][position.column]?.setSize(chessmanSize,chessmanSize)
                        chessmanActors[position.row][position.column]?.setCenter(
                                originLocationX + (position.column * chessboardUnitWidth),
                                originLocationY + (position.row * chessboardUnitHeight))

                        //显示桌面上一步下棋轨迹提示 (提棋子点 -> 落子点)
                        oriActor.isVisible = true
                        oriActor.setCenter(originLocationX + oldColumn * chessboardUnitWidth,
                                originLocationY + oldRow * chessboardUnitHeight)

                        desActor.isVisible = true
                        desActor.setCenter(originLocationX + (position.column * chessboardUnitWidth),
                                originLocationY + (position.row * chessboardUnitHeight))

                        ChessHelper.dropChessman()
                        dropSound.play()
                        Gdx.app.log(LogTag.OPLog, "SuccessFinish")
                }

                    MoveResult.DesIsSelf -> {// 如果 des 棋子就是 picked 棋子，则 drop 棋子

                        //actor 纹理改变成 freedom 状态,drop 棋子
                        EngineTools.replaceActorTexture(chessmanActors[position.row][position.column],
                                ChessTools.queryResPathByNormalChessman(chessmanActors[position.row][position.column]?.chessman))

                        chessmanActors[position.row][position.column]?.setSize(chessmanSize,chessmanSize)

                        //打开上一步下棋轨迹
                        oriActor.isVisible = true
                        desActor.isVisible = true

                        ChessHelper.dropChessman()
                        dropSound.play()
                        Gdx.app.log(LogTag.OPLog, "DesIsSelfFinish")
                    }

                    MoveResult.UnSupportChessRule -> {

                    }

                    MoveResult.NoPickedChessMan -> {

                    }
                }
            }
        }
    }
}