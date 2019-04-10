package com.pimuseum.game.chinesechess.screen

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor

/**
 * @Author : xialonghua
 * @Date : Create in 2019/4/10
 * @Description : a new file
 */
class MyActor: Actor() {

    private var img: Texture = Texture("badlogic.jpg")
    private var sprite = Sprite(img)

    init {
        setSize(sprite.width, sprite.height)
    }


    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch?.draw(sprite, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
//        sprite.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        rotation += 1
        setPosition(x + 1, y + 1)
    }
}