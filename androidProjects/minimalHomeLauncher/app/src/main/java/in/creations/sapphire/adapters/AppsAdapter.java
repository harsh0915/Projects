package in.creations.sapphire.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.creations.sapphire.R;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private Context context;
    private List<String> appsList;
    private onAppClickListener listener;
    private int visibleAppCount;

    public interface onAppClickListener {
        void appClicked();
    }

    public AppsAdapter(Context context, List<String> appsList, int visibleAppCount) {
        this.context = context;
        this.appsList = appsList;
        this.visibleAppCount = visibleAppCount;
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder holder, int position) {

        holder.appName.setText(appsList.get(position));

        holder.appName.setOnClickListener(v -> {
            listener.appClicked();
        });
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView appName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            appName = itemView.findViewById(R.id.appName);
        }
    }
}
