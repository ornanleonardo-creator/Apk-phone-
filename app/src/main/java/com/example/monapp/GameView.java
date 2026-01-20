package com.example.monapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View {

    Paint paint = new Paint();
    float playerX;
    float playerY;
    float blockX;
    float blockY;
    int score = 0;
    Random random = new Random();

    public GameView(Context context) {
        super(context);
        playerX = 300;
        playerY = 800;
        resetBlock();
    }

    void resetBlock() {
        blockX = random.nextInt(600);
        blockY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        // Joueur
        paint.setColor(Color.GREEN);
        canvas.drawRect(playerX, playerY, playerX + 100, playerY + 100, paint);

        // Bloc ennemi
        paint.setColor(Color.RED);
        canvas.drawRect(blockX, blockY, blockX + 100, blockY + 100, paint);

        // Score
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText("Score: " + score, 30, 50, paint);

        blockY += 10;

        // Collision
        if (blockY + 100 >= playerY &&
            blockX < playerX + 100 &&
            blockX + 100 > playerX) {

            paint.setTextSize(80);
            canvas.drawText("GAME OVER", 150, 400, paint);
            return;
        }

        if (blockY > getHeight()) {
            score++;
            resetBlock();
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE ||
            event.getAction() == MotionEvent.ACTION_DOWN) {

            playerX = event.getX() - 50;
        }
        return true;
    }
          }
