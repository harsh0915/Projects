package in.creations.sapphire;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
    private FrameLayout container;
    private ImageView menuButton;
    private DrawerLayout homePage;
    private ConstraintLayout content;
    private RenderEffect blurEffect;
    private ObjectAnimator bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        initializeUiElements();
        setupClickListeners();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void initializeUiElements() {

        appsRCV = findViewById(R.id.appsRCV);
        container = findViewById(R.id.container);
        menuButton = findViewById(R.id.menuButton);
        homePage = findViewById(R.id.homePage);
        content = findViewById(R.id.content);

        //animator
//        setAnimator();

        //registering the launcher intent
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        packageManager = getPackageManager();
        appsList = packageManager.queryIntentActivities(launcherIntent, 0);

        //set up adapter for recycler view
        appsAdapter = new AppsAdapter(this, getAppLabels(appsList), getVisibleAppCount(), this);
        appsRCV.setLayoutManager(new LinearLayoutManager(this));
        appsRCV.setAdapter(appsAdapter);

        blurEffect = RenderEffect.createBlurEffect(5f, 5f, Shader.TileMode.CLAMP);

//        homePage.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        homePage.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//                // Move content as drawer slides
                float moveFactor = drawerView.getWidth() * slideOffset;
                content.setTranslationX(-moveFactor);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

                //checks if drawer is being opened applies a blur effect
                if (
                        (newState == DrawerLayout.STATE_DRAGGING)
                                || (newState == DrawerLayout.STATE_IDLE && homePage.isDrawerOpen(container))
                                || (newState == DrawerLayout.STATE_SETTLING)
                ) {
//                    bounce.start();
                    menuButton.setImageResource(R.drawable.menu_icon);
                    content.setRenderEffect(blurEffect);
                } else {
//                    bounce.start();
                    menuButton.setImageResource(R.drawable.outlined_menu_icon);
                    content.setRenderEffect(null);
                }
            }
        });
    }

    private void setupClickListeners() {

        menuButton.setOnClickListener(v -> {

            if (!homePage.isDrawerOpen(container))
                homePage.openDrawer(GravityCompat.END);
            else
                homePage.closeDrawer(GravityCompat.END);

        });
    }

//    public void setAnimator() {
//        bounce = ObjectAnimator.ofFloat(menuButton, "translationY", 0, 20);
//        bounce.setDuration(200);
//        bounce.setRepeatMode(ObjectAnimator.REVERSE);
//        bounce.setRepeatCount(1);
//        bounce.setInterpolator(new AccelerateInterpolator());
//
//        bounce.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(@NonNull Animator animation) {
//                if (homePage.isDrawerOpen(GravityCompat.END))
//                    menuButton.setImageResource(R.drawable.menu_icon);
//                else
//                    menuButton.setImageResource(R.drawable.outlined_menu_icon);
//            }
//
//            @Override
//            public void onAnimationEnd(@NonNull Animator animation) {
//                if (homePage.isDrawerOpen(GravityCompat.END))
//                    menuButton.setImageResource(R.drawable.menu_icon);
//                else
//                    menuButton.setImageResource(R.drawable.outlined_menu_icon);
//            }
//
//            @Override
//            public void onAnimationCancel(@NonNull Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(@NonNull Animator animation) {
//
//            }
//        });
//    }

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