package in.creations.sapphire.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

public class AppsList {

        // This method will now correctly return only launchable applications
        public static List<ApplicationInfo> getAllInstalledApps(PackageManager packageManager) {
            // 1. Create an Intent that represents the "main" entry point for an application
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);

            // 2. Add the LAUNCHER category to filter for apps that appear in the launcher
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 3. Query the PackageManager for all activities that can handle this Intent.
            // The '0' flag means we don't need any special extra information.
            // @SuppressLint("QueryPackageManager") is often used here to suppress a lint warning
            // that suggests checking for permissions, but for this specific query, it's generally not needed.
            @SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(mainIntent, 0);

            // 4. Extract the ApplicationInfo from each ResolveInfo
            List<ApplicationInfo> launchableApps = new ArrayList<>();
            for (ResolveInfo info : resolveInfos) {
                // Each ResolveInfo contains information about an activity that matches the intent.
                // We're interested in the applicationInfo associated with that activity.
                launchableApps.add(info.activityInfo.applicationInfo);
            }
            return launchableApps;
        }
}
