package com.sayed.sayedsimplealarm.customrecycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.sayedsimplealarm.Allintefaces.IAddEmptyView;

public class EmptyRecyclerView extends RecyclerView implements IAddEmptyView {
    private static final String TAG = EmptyRecyclerView.class.getSimpleName();
    private final AdapterDataObserver mRecyclerDataObserver = new EmptyRecyclerDataObserver();
    private  View mEmptyView ;

    public EmptyRecyclerView(@NonNull Context context) {
        super(context);

    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);

        if(adapter != null) {
            adapter.registerAdapterDataObserver(mRecyclerDataObserver);
        }

        mRecyclerDataObserver.onChanged();
    }

    private final class EmptyRecyclerDataObserver extends AdapterDataObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            Adapter<?> adapter = getAdapter();
            if(adapter != null && mEmptyView != null) {
                if(adapter.getItemCount() == 0) {
                    //adapter e item count 0 hoile
                    //empty view show korbo else adapter show korbo
                    mEmptyView.setVisibility(VISIBLE);
                    EmptyRecyclerView.this.setVisibility(INVISIBLE);
                }else {
                    mEmptyView.setVisibility(INVISIBLE);
                    EmptyRecyclerView.this.setVisibility(VISIBLE);
                }
            }
        }
    }

    @Override
    public void setmEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
    }
}
