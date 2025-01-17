package com.sunil_poudel.accessibilityapplication.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.InputMethod;
import android.content.SharedPreferences;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.NonNull;

public class MyAccessibilityService extends AccessibilityService {
    private SharedPreferences sharedPreferences;
    private long scrollStartTime = 0;
    private long scrollEndTime = 0;
    private long totalScrollTime = 0;

    @Override
    public void onCreate(){
        super.onCreate();

        sharedPreferences = getSharedPreferences("ScrollTracker", MODE_PRIVATE);
        sharedPreferences.getLong("totalScrollTime", 0);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if(event.getPackageName()!=null && event.getPackageName().toString().equals("com.google.android.youtube")) {
            if (event.getEventTime() == AccessibilityEvent.TYPE_TOUCH_INTERACTION_START) {
                scrollStartTime = System.currentTimeMillis();
            }
            if (event.getEventTime() == AccessibilityEvent.TYPE_TOUCH_INTERACTION_END) {
                if(wasScrolling(event)){
                    scrollEndTime = System.currentTimeMillis();
                    totalScrollTime += (scrollEndTime-scrollStartTime);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong("totalScrollTime", totalScrollTime);
                    editor.apply();
                }

                scrollStartTime=0;
            }
        }
    }
    public boolean wasScrolling(AccessibilityEvent event){
        return event.getEventTime()==AccessibilityEvent.TYPE_VIEW_SCROLLED;
    }

    @Override
    public void onInterrupt() {

    }
}
