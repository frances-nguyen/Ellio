package com.jenho.ellioandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import com.jenho.framework.util.InputHandler;
import com.jenho.framework.util.Painter;
import com.jenho.game.state.LoadState;
import com.jenho.game.state.State;

public class GameView extends SurfaceView implements Runnable{

    private Bitmap gameImage; //Create an offscreen image and render it to the screen when ready.
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics; //Handle currentState's draw calls by drawing the requested images to the gameCanvas.

    private Thread gameThread;
    private volatile boolean running = false;
    private volatile State currentState;

    private InputHandler inputHandler;

    public GameView(Context context, int gameWidth, int gameHeight){
        super(context);
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565); //Less transparency, cover entire screen
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage); //To draw an image onto our gameImage, we must draw onto its Canvas
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                //Log.d("GameView","Surface Created"); // Print debug messages to LogCat
                initInput();
                if(currentState == null){
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                pauseGame();
            }
        });
    }

    public GameView(Context context){
        super(context);
    }

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    //Called every time our surface is created i.e. when the app is running first time or being resumed
    private void initInput(){
        if (inputHandler == null){
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while(running){
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender)/1000000L;
            sleepDurationMillis = Math.max(2,17-updateDurationMillis);

            try{
                Thread.sleep(sleepDurationMillis);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void initGame(){
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    private void pauseGame(){
        running = false;
        while(gameThread.isAlive()){
            try{
                gameThread.join(); //Tell gameThread to stop executing when application is pause
                break;
            } catch (InterruptedException e){

            }
        }
    }

    private void updateAndRender(long delta){
        currentState.update(delta/1000f);
        currentState.render(graphics);
        renderGameImage();
    }

    private void renderGameImage(){
        Canvas screen = getHolder().lockCanvas(); //locks the canvas for drawing allowing only one thread to draw at a time
        if(screen != null){ //Verify that canvas is not null
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage, gameImageSrc, gameImageDst, null);
            getHolder().unlockCanvasAndPost(screen); //unlock canvas and end the drawing
        }
    }
}
