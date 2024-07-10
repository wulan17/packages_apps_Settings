/*
 * Copyright (C) 2023-2024 Afterlife Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;

public class afterlifeLogoController extends BasePreferenceController
implements View.OnTouchListener {

    Context mContext;
    ImageView mAfterlifeLogo;

    private AnimatedVectorDrawable avdClicked;
    private AnimatedVectorDrawable avd;
    private Boolean isClicked = false;

    public afterlifeLogoController(Context context, String key) {
        super(context, key);
        mContext = context;
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        LayoutPreference mPreference = screen.findPreference(getPreferenceKey());
        final ImageView mAfterlifeLogo = mPreference.findViewById(getResources("id/after_life_logo"));

        mAfterlifeLogo.setClickable(true);
        mAfterlifeLogo.setOnClickListener(view -> 
            {

                    avdClicked =  (AnimatedVectorDrawable)mContext.getResources().getDrawable(getResources("drawable/al_anim_reverse"));
                    avd = (AnimatedVectorDrawable)mContext.getResources().getDrawable(getResources("drawable/al_anim"));

                    AnimatedVectorDrawable drawable = isClicked ? avdClicked : avd;
                    drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                            @Override
                            public void onAnimationStart(Drawable drawable) {
                                super.onAnimationStart(drawable);
                            }

                            @Override
                            public void onAnimationEnd(Drawable drawable) {
                                super.onAnimationEnd(drawable);
                            }
                        });
                    isClicked = !isClicked;
                    mAfterlifeLogo.setImageDrawable(drawable);
                    drawable.start();


            });

    }

    public int getResources(String res) {
        return mContext.getResources().getIdentifier(res, null, mContext.getPackageName());
    }


    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        return true;
    }

}