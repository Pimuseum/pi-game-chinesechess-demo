package com.pimuseum.game.chinesechess.engine.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor


/**
 * Desc : ImageActor
 * Author : Jiervs
 * Date : 2019/5/16
 */
open class ImageActor : Actor {

    var region : TextureRegion? = null

    constructor() : super()

    constructor(region: TextureRegion): super() {
        this.region = region
        this.region?.let {it->
            setSize(it.regionWidth.toFloat(), it.regionHeight.toFloat())
        }
    }

    fun setRegionWithSize(region: TextureRegion) {
        this.region = region
        setSize(region.regionWidth.toFloat(), region.regionHeight.toFloat())
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        if (region == null || !isVisible) return

        // temp batch origin Color
        val tempBatchColor = batch?.color

        // set batch color by parentAlpha
        batch?.setColor(color.r, color.g, color.b, color.a * parentAlpha)

        // draw bound of texture region
        batch?.draw(region,
                x, y,
                originX, originY,
                width, height,
                scaleX, scaleY,
                rotation)

        // reset origin batch color
        batch?.color = tempBatchColor
    }

    fun setTopY(topY: Float) {
        y = topY - height
    }

    fun getTopY(): Float {
        return y + height
    }

    fun setRightX(rightX: Float) {
        x = rightX - width
    }

    fun getRightX(): Float {
        return x + width
    }

    fun setCenter(centerX: Float, centerY: Float) {
        setCenterX(centerX)
        setCenterY(centerY)
    }

    fun setCenterX(centerX: Float) {
        x = centerX - width / 2.0f
    }

    fun setCenterY(centerY: Float) {
        y = centerY - height / 2.0f
    }

}