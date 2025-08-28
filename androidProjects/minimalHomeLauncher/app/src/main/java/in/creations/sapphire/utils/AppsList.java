package in.creations.sapphire.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class AppsList {

        public static List<ResolveInfo> getAllInstalledApps(PackageManager packageManager) {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            @SuppressLint("QueryPermissionsNeeded")
            List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(mainIntent, 0);
            return resolveInfos;
        }
}
