package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private GestureDetector mGestureDetector;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singTaptextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);

    }


    /**
     * Sends the touch event to all the children in ViewGroup
     * e.g. ScrollView down to the TextView
     * @param ev The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }


    /**
     * Handles a single-tap gesture. Not part of double-tap.
     * @param motionEvent The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        // Display the message and increment the counter
        gesturesLogTextView.append("\nonSingleTap gesture occurred");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     * Responds to a double-tap gesture. Double-tap is the succession of two single tap
     * gestures within a duration
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap gesture occurred");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double tap, another event occurs (including move).
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTapEvent gesture occurred");
        return true;
    }

    /**
     * User made initial contact with device
     * Every gesture begins with onDown.
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.setText("\nonDown gesture occurred");
        return false;
    }

    /**
     * Down event where user does not let go, short duration of time
     * Press and hold, but not long
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.setText("\nonShowPress gesture occurred");
    }

    /**
     * Similar to onSingleTapConfirmed, but it could be part of a double-tap
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.setText("\nonSingleTapUp gesture occurred");
        return true;
    }


    /**
     * Down event, followed by a press and a lateral movement,
     * without relinguishing contact with device.
     * @param motionEvent  the event where scroll started.
     * @param motionEvent1  the event where scroll stopped.
     * @param distanceX The distance in X direction (pixels).
     * @param distanceY The distance in Y direction (pixels)
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nScroll gesture occurred, distanceX= " + distanceX + " to " + distanceY );
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }


    /**
     * Down event, followed by a long hold.
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonLongPress gesture occurred");
        longPressTextView.setText(String.valueOf(++longPresses));

    }

    /**
     * Similiar to scroll, with faster velocity and user releases contact with device
     * @param motionEvent
     * @param motionEvent1
     * @param v Velocity in x-direction (pixels/second).
     * @param v1 Velocity in y-direction (pixels/second).
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nFling gesture occurred, velocityX = " + v + ", velocityY = " + v1 );
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    public void clearAll(View v) {
        gesturesLogTextView.setText("");
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        singleTapTextView.setText(String.valueOf(singleTaps));
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        longPressTextView.setText(String.valueOf(longPresses));
        scrollTextView.setText(String.valueOf(scrolls));
        flingTextView.setText(String.valueOf(flings));
    }
}
