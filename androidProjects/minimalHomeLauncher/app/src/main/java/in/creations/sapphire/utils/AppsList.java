package in.creations.sapphire.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import in.creations.sapphire.DataModels.Tile;
import in.creations.sapphire.DataModels.TileType;

public class AppsList {

    private static final String TAG = "AppListDebugger";
    public static List<Tile> getAllInstalledApps(PackageManager packageManager) {
        List<Tile> list = new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        @SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(mainIntent, 0);
        for (int i = 1; i < resolveInfos.size(); i++) {
            Tile tile = getTile(i, resolveInfos);
            Log.i(TAG," => "+tile.getType());
            list.add(tile);
        }
        return list;
    }

    @NonNull
    private static Tile getTile(int i, List<ResolveInfo> resolveInfos) {
        int r = i % 2;
        int q = i / 2;
        Tile tile;
        //odd remainder
        if (r == 0) {
            tile = new Tile(TileType.SMALL_TILE, resolveInfos.get(i - 1));
        }
        //even remainder
        else {
            //with odd quotient
            if (q % 2 == 1) {
                tile = new Tile(TileType.WIDE_TILE, resolveInfos.get(i - 1));
            }
            //with even quotient
            else {
                tile = new Tile(TileType.NORMAL_TILE, resolveInfos.get(i - 1));
            }
        }
        return tile;
    }
}
