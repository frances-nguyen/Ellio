package com.jenho.framework.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.jenho.game.state.State;
import com.jenho.ellioandroid.GameMainActivity;

public class InputHandler implements OnTouchListener{

    private State currentState;

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    /* Called whenever the player touches the screen.
     * Return true if we responded to the touch event, false otherwise.*/
    @Override
    public boolean onTouch(View v, MotionEvent event){

        /* Event.getX() and event.getY() tell us the coordinates of the touch with respect to the screen resolution
         * This is accomplish by dividing event coordinates by screen's dimensions v.getWidth() & v.getHeight()
         * and multiplying by game's dimensions GameMainActivity....
         */
        int scaledX = (int) ((event.getX() / v.getWidth()) * GameMainActivity.GAME_WIDTH);
        int scaledY = (int) ((event.getY() / v.getHeight()) * GameMainActivity.GAME_HEIGHT);
        return currentState.onTouch(event, scaledX, scaledY);
    }
}
