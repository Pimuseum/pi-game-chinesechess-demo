package com.pimuseum.game.chinesechess.engine.actor

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class AnimationActor : ImageActor {

    // 默认先显示第 0 帧
    var animation: Animation<TextureRegion>? = null
        set(animation) {
            field = animation
            if (this.animation != null) {
                this.animation?.keyFrames?.let { it->
                        region = it[0]
                }
            }
        }

    /** 是否播放动画, 如果不播放动画, 则固定显示指定的帧  */
    /**
     * 设置是否需要播放动画 <br></br>
     *
     * true: 按 Animation 对象指定的播放模式播放动画 <br></br>
     * false: 不播放动画, 始终显示 fixedShowKeyFrameIndex 指定的关键帧 <br></br>
     */
    var isPlayAnimation = true

    /** 不播放动画时固定显示的帧索引  */
    /**
     * 设置不播放动画时固定显示的关键帧索引
     */
    var fixedShowKeyFrameIndex: Int = 0

    /** 时间步 delta 的累加值  */
    private var stateTime: Float = 0.toFloat()

    constructor()

    constructor(frameDuration: Float, keyFrames: Array<out TextureRegion>) : this(Animation<TextureRegion>(frameDuration, keyFrames))

    constructor(frameDuration: Float, vararg keyFrames: TextureRegion) : this(Animation<TextureRegion>(frameDuration, *keyFrames))

    constructor(animation: Animation<TextureRegion>) {
        this.animation = animation
    }

    override fun act(delta: Float) {
        super.act(delta)
        if (this.animation != null) {
            var region: TextureRegion? = null
            if (isPlayAnimation) {
                // 如果需要播放动画, 则累加时间步, 并按累加值获取需要显示的关键帧
                stateTime += delta
                region = this.animation!!.getKeyFrame(stateTime)
            } else {
                // 不需要播放动画, 则获取 fixedShowKeyFrameIndex 指定的关键帧
                val keyFrames = this.animation!!.keyFrames
                if (fixedShowKeyFrameIndex >= 0 && fixedShowKeyFrameIndex < keyFrames.size) {
                    region = keyFrames[fixedShowKeyFrameIndex]
                }
            }
            // 设置当前需要显示的关键帧
            this.region = region
        }
    }

}























