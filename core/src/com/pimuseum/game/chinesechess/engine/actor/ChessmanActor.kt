package com.pimuseum.game.chinesechess.engine.actor

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.pimuseum.game.chinesechess.model.chessman.Chessman

/**
 * Desc : ChessmanActor
 * Author : Jiervs
 * Date : 2019/5/17
 */
class ChessmanActor : ImageActor {

    var  chessman : Chessman? = null

    constructor() : super()

    constructor(region: TextureRegion,chessman : Chessman): super(region) {
        this.chessman = chessman
    }

}