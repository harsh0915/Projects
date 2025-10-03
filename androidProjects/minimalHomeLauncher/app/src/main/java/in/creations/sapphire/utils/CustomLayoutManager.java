package in.creations.sapphire.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class CustomLayoutManager extends RecyclerView.LayoutManager {

    private final int view_width;

    public CustomLayoutManager(Context context) {
        super();
        view_width = 300;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        View view = recycler.getViewForPosition(0);
//        if(view.getRootView()==)
        int left = 0;
        int right = left + view_width;
        int top = 0;
        int bot = top + view_width;
        layoutDecorated(view, left, top, right, bot);
    }
}
