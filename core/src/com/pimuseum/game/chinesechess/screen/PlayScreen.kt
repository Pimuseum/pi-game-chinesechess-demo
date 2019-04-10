package com.pimuseum.game.chinesechess.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.viewport.StretchViewport



/**
 * @Author : xialonghua
 * @Date : Create in 2019/4/10
 * @Description : a new file
 */
class PlayScreen: ScreenAdapter() {
    private lateinit var stage: Stage


    override fun show() {
        super.show()

        val viewport = StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        viewport.apply(true)
        stage = Stage(viewport)

        stage.addActor(MyActor())
    }

    override fun render(delta: Float) {
        super.render(delta)
        stage.act(delta)
        stage.draw()
    }

}