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

import in.creations.sapphire.R;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private Context context;
    private List<ResolveInfo> appsList;
    private PackageManager packageManager;
    private int visibleAppCount;

    public AppsAdapter(Context context, List<ResolveInfo> appsList, int visibleAppCount) {
        this.context = context;
        this.appsList = appsList;
        this.visibleAppCount = visibleAppCount;
        this.packageManager = context.getPackageManager();
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_small_square_tile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder holder, int position) {
        ResolveInfo resolveInfo = appsList.get(position);
        boolean isSystemApp = (resolveInfo.activityInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
//        if (isSystemApp) {
            holder.appName.setText(resolveInfo.loadLabel(packageManager));
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
//        }
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView appName;
        private final ImageView appIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.appIcon);
            appName = itemView.findViewById(R.id.appName);
        }
    }
}
