package in.creations.sapphire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.creations.sapphire.adapters.AppsAdapter;

public class MainPage extends AppCompatActivity implements AppsAdapter.onAppClickListener {

    private RecyclerView appsRCV;
    private AppsAdapter appsAdapter;
    private List<ResolveInfo> appsList;
    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        initializeUiElements();
        setupClickListeners();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void initializeUiElements() {
        appsRCV = findViewById(R.id.appsRCV);
        packageManager = getPackageManager();

        //registering the launcher intent
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appsList = packageManager.queryIntentActivities(launcherIntent, 0);

        //set up adapter for recycler view
        appsAdapter = new AppsAdapter(this, getAppLabels(appsList), getVisibleAppCount(), this);
        appsRCV.setLayoutManager(new LinearLayoutManager(this));
        appsRCV.setAdapter(appsAdapter);
    }

    private void setupClickListeners() {
    }

    private List<String> getAppLabels(List<ResolveInfo> appsList) {
        List<String> labels = new ArrayList<>();
        for (ResolveInfo app : appsList) {
            labels.add(app.loadLabel(packageManager).toString());
        }
        return labels;
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

    @Override
    public void appClicked(int position) {
        ResolveInfo resolveInfo = appsList.get(position);
        String packageName = resolveInfo.activityInfo.packageName;
        Intent launchApp = packageManager.getLaunchIntentForPackage(packageName);
        if (launchApp != null) {
            startActivity(launchApp);
        }
    }
}