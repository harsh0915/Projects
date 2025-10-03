package in.creations.sapphire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.creations.sapphire.DataModels.Tile;
import in.creations.sapphire.DataModels.TileType;
import in.creations.sapphire.adapters.AppsAdapter;
import in.creations.sapphire.utils.AppsList;
import in.creations.sapphire.utils.CustomLayoutManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appsRCV;
    private AppsAdapter appsAdapter;
    private List<Tile> appsList;
    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUiElements();
        setupClickListeners();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void initializeUiElements() {

        appsRCV = findViewById(R.id.appsRCV);
        //registering the launcher intent
//        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
//        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        packageManager = getPackageManager();
        appsList = AppsList.getAllInstalledApps(packageManager);
        appsAdapter = new AppsAdapter(this, appsList);
        CustomLayoutManager layoutManager = new CustomLayoutManager(this);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                Tile tile = appsList.get(position);
//                return tile.getTileDimension(tile.getType());
//            }
//        });

        appsRCV.setLayoutManager(layoutManager);
        appsRCV.setAdapter(appsAdapter);
    }

    private void setupClickListeners() {
    }
}
