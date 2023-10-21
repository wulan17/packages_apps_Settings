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

import android.view.View;
import android.content.res.ColorStateList;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import android.graphics.Color;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.*;
import androidx.preference.*;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import android.content.res.TypedArray;
import androidx.core.content.res.TypedArrayUtils;
import declan.prjct.utils.DeclanUtils;
import android.content.Context;
import com.android.settings.R;

/** Helper for homepage preference to manage layout. */
public class AfterlifePreferenceLayoutHelper {

  /**private View mIcon;
    *private View mText;
    *private boolean mIconVisible = true;
    *private int mIconPaddingStart = -1;
    *private int mTextPaddingStart = -1;
    */
    private RelativeLayout layoutContainer;
	private View dividerRight, dividerBottom;

	public boolean cardBottom, cardRight, showDividerRight, showDividerBottom;
	public int cardType;

	private Context mContext;

    /** The interface for managing preference layouts on homepage */
    public interface AfterlifePreferenceLayout {
        /** Returns a {@link AfterlifePreferenceLayoutHelper}  */
        AfterlifePreferenceLayoutHelper getHelper();
    }

    public AfterlifePreferenceLayoutHelper(Preference preference) {
        preference.setLayoutResource(R.layout.afterlife_preference);
        mContext = preference.getContext();
    }

    /** Sets whether the icon should be visible 
    public void setIconVisible(boolean visible) {
        mIconVisible = visible;
        if (mIcon != null) {
            mIcon.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }*/

    /** Sets the icon padding start 
    public void setIconPaddingStart(int paddingStart) {
        mIconPaddingStart = paddingStart;
        if (mIcon != null && paddingStart >= 0) {
            mIcon.setPaddingRelative(paddingStart, mIcon.getPaddingTop(), mIcon.getPaddingEnd(),
                    mIcon.getPaddingBottom());
        }
    }*/

    /** Sets the text padding start 
    public void setTextPaddingStart(int paddingStart) {
        mTextPaddingStart = paddingStart;
        if (mText != null && paddingStart >= 0) {
            mText.setPaddingRelative(paddingStart, mText.getPaddingTop(), mText.getPaddingEnd(),
                    mText.getPaddingBottom());
        }
    }*/

    void onBindViewHolder(PreferenceViewHolder holder) {
        //mIcon = holder.findViewById(R.id.icon_frame);
        //mText = holder.findViewById(R.id.text_frame);
        //setIconVisible(mIconVisible);
        //setIconPaddingStart(mIconPaddingStart);
        //setTextPaddingStart(mTextPaddingStart);
        layoutContainer = (RelativeLayout) holder.itemView;
        //layoutContainer = holder.findViewById(R.id.layout_countainer);
        dividerRight = holder.findViewById(R.id.divider_right);
		dividerBottom = holder.findViewById(R.id.divider_bottom);

		updateLayoutContainer();
		updateDividerView();
		updateCardBackground();
    }

    public void updateLayoutContainer() {
		LayoutParams layoutParams = layoutContainer.getLayoutParams();
		int margin = DeclanUtils.dpToPx(16);
		if (layoutParams != null) {
			MarginLayoutParams marginParams = (MarginLayoutParams) layoutParams;
			if (cardRight && !cardBottom) {
				marginParams.setMargins(marginParams.getMarginStart(), marginParams.topMargin, margin, marginParams.bottomMargin);
			} else if (cardBottom && cardRight) {
				marginParams.setMargins(marginParams.getMarginStart(), marginParams.topMargin, margin, margin);
			} else if (!cardRight && cardBottom) {
				marginParams.setMargins(margin, marginParams.topMargin, marginParams.getMarginEnd(), margin);
			} else {
				marginParams.setMargins(margin, marginParams.topMargin, marginParams.getMarginEnd(), marginParams.bottomMargin);
			}
		}
	}

	public void updateDividerView() {
		if (showDividerRight) {
			dividerRight.setVisibility(View.VISIBLE);
		} else {
			dividerRight.setVisibility(View.GONE);
		}
		if (showDividerBottom) {
			dividerBottom.setVisibility(View.VISIBLE);
		} else {
			dividerBottom.setVisibility(View.GONE);
		}
	}

	public void updateCardBackground() {
		int rippleColor = DeclanUtils.getColorAttr(mContext, android.R.attr.colorControlHighlight);
		float radius = DeclanUtils.dpToPx(28);
		GradientDrawable gd = new GradientDrawable();
		gd.setColor(DeclanUtils.getLightDarkColor(mContext,R.color.card_bg,  R.color.card_bg));
		if (cardType == 0) {
			//Top Left//
			gd.setCornerRadii(new float[]{radius, radius, 0, 0, 0, 0, 0, 0});
		} else if (cardType == 1) {
			//Top Right//
			gd.setCornerRadii(new float[]{0, 0, radius, radius, 0, 0, 0, 0});
		} else if (cardType == 2) {
			//Middle//
			gd.setCornerRadius(0);
		}else if (cardType == 3) {
			//Bottom Left//
			gd.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, radius, radius});
		} else if (cardType == 4) {
			//Bottom Right//
			gd.setCornerRadii(new float[]{0, 0, 0, 0, radius, radius, 0, 0});
		}
		layoutContainer.setBackground(new RippleDrawable(ColorStateList.valueOf(rippleColor), gd, null));
	}
}