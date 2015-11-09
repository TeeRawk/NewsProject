package com.technical.saion.newsapp.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsDecoration  extends RecyclerView.ItemDecoration {
    private int offset;
    public NewsDecoration(int offset) {
        super();
        this.offset=offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom=offset;
        outRect.top=offset;

    }
}
