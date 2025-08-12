package in.creations.sapphire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

import in.creations.sapphire.adapters.AppsAdapter;
import in.creations.sapphire.utils.AppsList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appsRCV;
    private AppsAdapter appsAdapter;
    private List<ApplicationInfo> appsList;
    private PackageManager packageManager;
    private FrameLayout container;
    private TextView topBar;
//    private ImageView menuButton;
    private DrawerLayout homePage;
    private ConstraintLayout content;
    private RenderEffect blurEffect;

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
        container = findViewById(R.id.container);
        homePage = findViewById(R.id.homePage);
        content = findViewById(R.id.content);

        //registering the launcher intent
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        packageManager = getPackageManager();
        appsList = AppsList.getAllInstalledApps(packageManager);
        topBar = findViewById(R.id.topBar);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        appsAdapter = new AppsAdapter(this, appsList, getVisibleAppCount());
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                // Read your tile type from the data list
//                switch (tileList.get(position).getSize()) {
//                    case SMALL: return 1; // 1 column wide
//                    case MEDIUM: return 2; // 2 columns wide
//                    case WIDE: return 4; // full width
//                    default: return 1;
//                }
//            }
//        });
        appsRCV.setLayoutManager(layoutManager);

        appsRCV.setAdapter(appsAdapter);

        blurEffect = RenderEffect.createBlurEffect(5f, 5f, Shader.TileMode.CLAMP);

        homePage.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float moveFactor = drawerView.getWidth() * slideOffset;
//                content.setTranslationX(-moveFactor);
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
//                    menuButton.setImageResource(R.drawable.menu_icon);
                    content.setRenderEffect(blurEffect);
                } else {
//                    menuButton.setImageResource(R.drawable.outlined_menu_icon);
                    content.setRenderEffect(null);
                }
            }
        });
    }

    private void setupClickListeners() {

        topBar.setOnClickListener(v->{
            if (!homePage.isDrawerOpen(container))
                homePage.openDrawer(GravityCompat.END);
            else
                homePage.closeDrawer(GravityCompat.END);
        });
//        menuButton.setOnClickListener(v -> {
//
//            if (!homePage.isDrawerOpen(container))
//                homePage.openDrawer(GravityCompat.END);
//            else
//                homePage.closeDrawer(GravityCompat.END);
//
//        });
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

//holder.itemView.setOnLongClickListener(v -> {
//PopupMenu menu = new PopupMenu(v.getContext(), v);
//    menu.getMenu().add("Small");
//    menu.getMenu().add("Medium");
//    menu.getMenu().add("Wide");
//
//    menu.setOnMenuItemClickListener(item -> {
//        if (item.getTitle().equals("Small")) {
//        appTiles.get(holder.getAdapterPosition())
//        .setTileSize(AppTile.TileSize.SMALL);
//        } else if (item.getTitle().equals("Medium")) {
//        appTiles.get(holder.getAdapterPosition())
//        .setTileSize(AppTile.TileSize.MEDIUM);
//        } else {
//                appTiles.get(holder.getAdapterPosition())
//        .setTileSize(AppTile.TileSize.WIDE);
//        }
//notifyItemChanged(holder.getAdapterPosition());
//        return true;
//        });
//        menu.show();
//    return true;
//            });
