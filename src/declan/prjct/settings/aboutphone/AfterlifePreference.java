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

package declan.prjct.settings.aboutphone;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/** A customized layout for homepage preference. */
public class AfterlifePreference extends Preference implements
        AfterlifePreferenceLayoutHelper.AfterlifePreferenceLayout {

    private static final String SETTINGSNS = "http://schemas.android.com/apk/res-auto";

    private final AfterlifePreferenceLayoutHelper mHelper;

    public AfterlifePreference(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mHelper = new AfterlifePreferenceLayoutHelper(this);
        mHelper.cardType = attrs.getAttributeIntValue(SETTINGSNS, "cardType", -1);
		mHelper.cardRight = attrs.getAttributeBooleanValue(SETTINGSNS, "cardRight", false);
		mHelper.cardBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "cardBottom", false);
		mHelper.showDividerRight = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerRight", false);
		mHelper.showDividerBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerBottom", false);
    }

    public AfterlifePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper = new AfterlifePreferenceLayoutHelper(this);
        mHelper.cardType = attrs.getAttributeIntValue(SETTINGSNS, "cardType", -1);
		mHelper.cardRight = attrs.getAttributeBooleanValue(SETTINGSNS, "cardRight", false);
		mHelper.cardBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "cardBottom", false);
		mHelper.showDividerRight = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerRight", false);
		mHelper.showDividerBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerBottom", false);
    }

    public AfterlifePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = new AfterlifePreferenceLayoutHelper(this);
        mHelper.cardType = attrs.getAttributeIntValue(SETTINGSNS, "cardType", -1);
		mHelper.cardRight = attrs.getAttributeBooleanValue(SETTINGSNS, "cardRight", false);
		mHelper.cardBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "cardBottom", false);
		mHelper.showDividerRight = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerRight", false);
		mHelper.showDividerBottom = attrs.getAttributeBooleanValue(SETTINGSNS, "showDividerBottom", false);
    }

    public AfterlifePreference(Context context) {
        super(context);
        mHelper = new AfterlifePreferenceLayoutHelper(this);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        mHelper.onBindViewHolder(holder);
    }

    @Override
    public AfterlifePreferenceLayoutHelper getHelper() {
        return mHelper;
    }
}