package in.creations.sapphire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import in.creations.sapphire.adapters.AppsAdapter;
import in.creations.sapphire.utils.AppsList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appsRCV;
    private AppsAdapter appsAdapter;
    private List<ResolveInfo> appsList;
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
        appsAdapter = new AppsAdapter(this, appsList, getVisibleAppCount());
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.SPACE_EVENLY);
        appsRCV.setLayoutManager(layoutManager);
        appsRCV.setAdapter(appsAdapter);
    }

    private void setupClickListeners() {
    }

    private int getVisibleAppCount() {

        int visibleItemCount = 0;

        LinearLayoutManager appsLayoutManager = (LinearLayoutManager) appsRCV.getLayoutManager();

        if (appsLayoutManager != null) {
            int firstVisibleItemPosition = appsLayoutManager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = appsLayoutManager.findLastVisibleItemPosition();
            visibleItemCount = firstVisibleItemPosition - lastVisibleItemPosition + 1;
        }

        return visibleItemCount;
    }
}
