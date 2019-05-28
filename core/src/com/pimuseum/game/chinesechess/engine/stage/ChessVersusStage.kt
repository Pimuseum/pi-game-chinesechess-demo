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
import com.pimuseum.game.chinesechess.engine.constant.GameMode
import com.pimuseum.game.chinesechess.engine.constant.LogTag
import com.pimuseum.game.chinesechess.engine.constant.GameRes
import com.pimuseum.game.chinesechess.engine.tools.EngineTools
import com.pimuseum.game.chinesechess.model.chessman.Chessman
import com.pimuseum.game.chinesechess.model.chessman.KingChessman
import com.pimuseum.game.chinesechess.model.companion.*
import com.pimuseum.game.chinesechess.model.observer.OperateObserver

/**
 * Desc : ChessVersusStage
 * Author : Jiervs
 * Date : 2019/5/16
 */
class ChessVersusStage(var mode : GameMode , viewport: Viewport) : Stage(viewport) , OperateObserver {

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
    private lateinit var oriActor : ChessmanActor
    private lateinit var desActor : ChessmanActor
    private lateinit var chessboardActor : ChessBoardActor
    private var chessmanActors : Array<Array<ChessmanActor?>>
            = Array(ChessHelper.RowCapacity){Array<ChessmanActor?>(ChessHelper.ColumnCapacity) { null}}

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
        chessboardHeight = chessboardWidth * (GameRes.RealChessboardHeight / GameRes.RealChessboardWidth)

        //chessboard logic size
        chessboardLogicWidth = chessboardWidth * ((GameRes.RealChessboardWidth - 2 * GameRes.RealHorizontalMargin) / GameRes.RealChessboardWidth)
        chessboardLogicHeight = chessboardHeight * ((GameRes.RealChessboardHeight - 2 * GameRes.RealVerticalMargin) / GameRes.RealChessboardHeight)

        //chessboard unit size (not square)
        chessboardUnitWidth = chessboardLogicWidth / 8
        chessboardUnitHeight = chessboardLogicHeight / 9

        chessmanSize = chessboardUnitWidth * 19 / 20

        //ensure logic original point location
        originLocationX = (width / 2F) - (5F * chessboardUnitWidth)
        originLocationY = (height / 2F) - (5.5F * chessboardUnitHeight)

        ChessHelper.observer = this

        initActors()
    }

    /**
     * Init Actors
     */
    private fun initActors(){
        //create background actor
        val bgTexture = Texture(Gdx.files.internal(GameRes.Actor_Game_Bg))
        bgActor = ImageActor(TextureRegion(bgTexture))
        bgActor.setCenter(width / 2, height / 2)
        addActor(bgActor)

        //create chessboard actor
        val chessboardTexture = Texture(Gdx.files.internal(GameRes.Actor_Chessboard))
        chessboardActor = ChessBoardActor(TextureRegion(chessboardTexture))
        chessboardActor.setSize(chessboardWidth,chessboardHeight)
        chessboardActor.setCenter(width / 2, height / 2)
        addActor(chessboardActor)

        //create origin actor
        val originTexture = Texture(Gdx.files.internal(GameRes.Actor_Origin_Trace))
        oriActor = ChessmanActor(TextureRegion(originTexture))
        oriActor.setSize(chessboardUnitWidth,chessboardUnitWidth)
        oriActor.setCenter(2 * width , 2 * height) //初始化时候先放在 Stage 外面
        oriActor.isVisible = false
        addActor(oriActor)

        //create destination actor
        val desTexture = Texture(Gdx.files.internal(GameRes.Actor_Des_Trace))
        desActor = ChessmanActor(TextureRegion(desTexture))
        desActor.setSize(chessboardUnitWidth,chessboardUnitWidth)
        desActor.setCenter(2 * width , 2 * height) //初始化时候先放在 Stage 外面
        desActor.isVisible = false
        addActor(desActor)

        //create sound
        pickSound = Gdx.audio.newSound(Gdx.files.internal(GameRes.Sound_Pick))
        dropSound = Gdx.audio.newSound(Gdx.files.internal(GameRes.Sound_Drop))

        //load chessmen
        ChessHelper.loadChessmen()

        //create bgm
//      bgm = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm.mp3"))
//      bgm.volume /= 4
//      bgm.play()
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

        return true
    }

    /**
     * 触摸棋盘以后的游戏逻辑步骤：
     *
     * 1.先查询象棋的棋子处于被选择状态还是自由状态
     *
     * 2.根据 状态操作 freedom:选取  pick:下棋
     */
    private lateinit var touchPosition : Position //当前触摸点转换为的当前象棋坐标

    private fun playChess(stageVector2: Vector2) {

        //触摸点转换为象棋坐标
        EngineTools.stageCovert2Position(stageVector2,originLocationX,originLocationY,chessboardUnitWidth,chessboardUnitHeight)?.let { position->

            touchPosition = position

            Gdx.app.log(LogTag.OPLog, "operationStatus : ${ChessHelper.operationStatus}")

            if(ChessHelper.operationStatus == OperationStatus.ChessFreedom) {// 棋子自由状态

                    //触摸点下是否存在棋子
                    ChessHelper.isExistChessmanByPosition(touchPosition)?.let { chessman ->

                        //pick 棋子 ，actor 纹理改变成 picked 状态
                        if (ChessHelper.pickChessman(touchPosition)) {

                            pickSound.play()

                            //隐藏上一步下棋轨迹
                            oriActor.isVisible = false
                            desActor.isVisible = false

                            EngineTools.replaceActorTexture(chessmanActors[touchPosition.row][touchPosition.column],
                                    ChessTools.queryResPathByPickedChessman(chessman))

                            chessmanActors[touchPosition.row][touchPosition.column]?.setSize(chessboardUnitWidth,chessboardUnitWidth)
                        }
                    }

            } else { // 棋子已选择状态，落下下棋

                val result = ChessHelper.moveChessman(touchPosition)
                Gdx.app.log(LogTag.OPLog, result.name)
            }
        }
    }

    /********************************     Chessboard    Operation    Observer     ***************************************/

    override fun onRemoveChessman(chessman: Chessman) { //判断是否是King，如果是则游戏结束

        if (chessman is KingChessman) {

            if (chessman.chessType == ChessType.Red) {
                Gdx.app.log(LogTag.OPLog, "Black Win")
            } else {
                Gdx.app.log(LogTag.OPLog, "Red Win")
            }

            clear()
            initActors()
        }
    }

    override fun onMoveChessman(row: Int, column: Int) {

        //调整 Actor 位置
        chessmanActors[touchPosition.row][touchPosition.column]?.let {
            root.removeActor(chessmanActors[touchPosition.row][touchPosition.column])
            chessmanActors[touchPosition.row][touchPosition.column] = null
        }

        chessmanActors[touchPosition.row][touchPosition.column] = chessmanActors[row][column]
        chessmanActors[row][column] = null

        //actor 纹理改变成 freedom 状态,drop 棋子
        EngineTools.replaceActorTexture(chessmanActors[touchPosition.row][touchPosition.column],
                ChessTools.queryResPathByNormalChessman(chessmanActors[touchPosition.row][touchPosition.column]?.chessman))

        chessmanActors[touchPosition.row][touchPosition.column]?.setSize(chessmanSize,chessmanSize)
        chessmanActors[touchPosition.row][touchPosition.column]?.
                setCenterByRoleType(touchPosition.row,touchPosition.column,
                        originLocationX,originLocationY,
                        chessboardUnitWidth,chessboardUnitHeight)

        //显示桌面上一步下棋轨迹提示 (提棋子点 -> 落子点)
        oriActor.isVisible = true
        oriActor.setCenterByRoleType(
                row,column,
                originLocationX,originLocationY,
                chessboardUnitWidth,chessboardUnitHeight)

        desActor.isVisible = true
        desActor.setCenterByRoleType(
                touchPosition.row,touchPosition.column,
                originLocationX,originLocationY,
                chessboardUnitWidth,chessboardUnitHeight)

        ChessHelper.dropChessman()
        dropSound.play()
    }

    override fun onDropBack(chessman: Chessman) {

        //actor 纹理改变成 freedom 状态,drop 棋子
        EngineTools.replaceActorTexture(chessmanActors[touchPosition.row][touchPosition.column],
                ChessTools.queryResPathByNormalChessman(chessmanActors[touchPosition.row][touchPosition.column]?.chessman))

        chessmanActors[touchPosition.row][touchPosition.column]?.setSize(chessmanSize,chessmanSize)

        //打开上一步下棋轨迹
        oriActor.isVisible = true
        desActor.isVisible = true

        ChessHelper.dropChessman()
        dropSound.play()

    }

    override fun onLoadChessmen() {

        for(row in 1 until ChessHelper.RowCapacity) {
            for (column in 1 until ChessHelper.ColumnCapacity) {

                ChessHelper.queryChessboardInfo()[row][column]?.let { chessman ->

                    //create chessman actor
                    ChessTools.queryResPathByNormalChessman(chessman)?.let { resPath->

                        val chessmanTexture = Texture(Gdx.files.internal(resPath))
                        val chessmanActor = ChessmanActor(TextureRegion(chessmanTexture),chessman)
                        chessmanActor.setSize(chessmanSize,chessmanSize)

                        chessmanActor.setCenterByRoleType(
                                chessman.position.row,chessman.position.column,
                                originLocationX,originLocationY,
                                chessboardUnitWidth,chessboardUnitHeight)

                        addActor(chessmanActor)
                        chessmanActors[chessman.position.row][chessman.position.column] = chessmanActor


//                        Gdx.app.log(LogTag.ChessLog, "${chessman.javaClass.simpleName}: ${chessman.position.row} *" +
//                                " ${chessman.position.column}")
                    }
                }
            }
        }
    }
}