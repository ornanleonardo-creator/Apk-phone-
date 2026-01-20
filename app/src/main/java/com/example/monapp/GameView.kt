package com.example.monapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class GameView(context: Context) : View(context) {

    private val paint = Paint()
    private var playerX = 300f
    private val playerY = 800f

    private var blockX = 0f
    private var blockY = 0f

    private var score = 0

    init {
        resetBlock()
    }

    private fun resetBlock() {
        blockX = Random.nextInt(0, 600).toFloat()
        blockY = 0f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.BLACK)

        // Joueur
        paint.color = Color.GREEN
        canvas.drawRect(playerX, playerY, playerX + 100, playerY + 100, paint)

        // Ennemi
        paint.color = Color.RED
        canvas.drawRect(blockX, blockY, blockX + 100, blockY + 100, paint)

        // Score
        paint.color = Color.WHITE
        paint.textSize = 40f
        canvas.drawText("Score : $score", 30f, 50f, paint)

        blockY += 12f

        // Collision
        if (blockY + 100 >= playerY &&
            blockX < playerX + 100 &&
            blockX + 100 > playerX
        ) {
            paint.textSize = 80f
            canvas.drawText("GAME OVER", 100f, 400f, paint)
            return
        }

        if (blockY > height) {
            score++
            resetBlock()
        }

        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE
        ) {
            playerX = event.x - 50
        }
        return true
    }
}
