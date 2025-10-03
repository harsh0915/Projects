package in.creations.sapphire.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.creations.sapphire.DataModels.Tile;
import in.creations.sapphire.DataModels.TileType;
import in.creations.sapphire.R;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private final Context context;
    private final List<Tile> appsList;
    private final PackageManager packageManager;

    public AppsAdapter(Context context, List<Tile> appsList) {
        this.context = context;
        this.appsList = appsList;
        this.packageManager = context.getPackageManager();
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TileType tileType = TileType.enumFromValue(viewType);
        if (tileType != null) {
            switch (tileType) {
                case WIDE_TILE:
                    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rectangle_tile, parent, false), TileType.WIDE_TILE);

                case SMALL_TILE:
                    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_square_tile, parent, false), TileType.SMALL_TILE);

                case NORMAL_TILE:
                default:
                    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_square_tile, parent, false), TileType.NORMAL_TILE);
            }
        } else {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_square_tile, parent, false), TileType.NORMAL_TILE);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return appsList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder holder, int position) {
        Tile tile = appsList.get(position);
        ResolveInfo resolveInfo = tile.getResolve_info();
        boolean isSystemApp = (resolveInfo.activityInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;

        if (getItemViewType(position) == TileType.WIDE_TILE.getValue()) {
            holder.appName.setText(resolveInfo.loadLabel(packageManager));
        }

        if (getItemViewType(position) == TileType.SMALL_TILE.getValue()) {
            holder.itemView.post(() -> {
                int originalHeight = holder.itemView.getHeight();
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = originalHeight / 2;
                holder.itemView.setLayoutParams(layoutParams);
            });
        }

        holder.appIcon.setImageDrawable(resolveInfo.loadIcon(packageManager));

        holder.itemView.setOnClickListener(v -> {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent launchApp = packageManager.getLaunchIntentForPackage(packageName);
            if (launchApp != null) {
                launchApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchApp);
            } else {
                Toast.makeText(context, "Cannot launch this app", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView appName;
        private final ImageView appIcon;

        public ViewHolder(@NonNull View itemView, TileType type) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.appIcon);
            appName = itemView.findViewById(R.id.appName);
        }
    }
}
