package com.hefny.hady.transitiontask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final ImageView imageView = findViewById(R.id.image2);

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                View.DragShadowBuilder builder = new View.DragShadowBuilder(imageView);
                imageView.startDrag(null, builder, null, 0);
                Log.d(TAG, "onScroll: X: " + distanceX + "Y: " + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        imageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                Log.d(TAG, "onDrag: " + event.toString());
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED: {
                        imageView.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case DragEvent.ACTION_DROP:
                    case DragEvent.ACTION_DRAG_ENDED: {
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(Main2Activity.this,
                                imageView, ViewCompat.getTransitionName(imageView));

                        startActivity(intent, options.toBundle());
                        break;
                    }
                }
                return true;
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

}
