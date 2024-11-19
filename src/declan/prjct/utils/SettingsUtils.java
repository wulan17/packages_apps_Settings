/*
 * Copyright (C) 2023 The AfterLife Project
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
package declan.prjct.utils;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.IActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.ServiceManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.settings.R;
import java.lang.ref.WeakReference;
public class SettingsUtils {
	
	public static void showSettingsRestartDialog(Context context) {
		showRestartDialog(context, R.string.restart_settings_title, R.string.restart_settings_message, () -> restartProcess(context, "com.android.settings"));
    }
    public static void showSystemUIRestartDialog(Context context) {
		showRestartDialog(context, R.string.restart_systemui_title, R.string.restart_systemui_message, () -> restartProcess(context, "com.android.systemui"));
    }
	
	public static void showRestartDialog(Context context, int title, int message, Runnable action) {
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(com.android.internal.R.string.ok, (dialog, id) -> {
					Handler handler = new Handler();
					handler.postDelayed(action, 1250);
				})
				.setNegativeButton(com.android.internal.R.string.cancel, null)
				.show();
    }
	
	public static void restartProcess(Context context, String processName) {
		new RestartTask(context, processName).execute();
    }
	
	private static class RestartTask extends AsyncTask<Void, Void, Void> {
		private final WeakReference<Context> mContext;
		private final String mProcessName;
		
		public RestartTask(Context context, String processName) {
			mContext = new WeakReference<>(context);
			mProcessName = processName;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				ActivityManager am = (ActivityManager) mContext.get().getSystemService(Context.ACTIVITY_SERVICE);
				if (am != null) {
					IActivityManager ams = ActivityManager.getService();
					for (ActivityManager.RunningAppProcessInfo app : am.getRunningAppProcesses()) {
						if (app.processName.contains(mProcessName)) {
							ams.killApplicationProcess(app.processName, app.uid);
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
    }
}