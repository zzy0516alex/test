package com.z.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinglan.scrolllayout.ScrollLayout;
import com.z.test.Utils.ScreenUtil;


public class ScrollFragment extends Fragment {

    public ScrollFragment() {
        // Required empty public constructor
    }

    public ScrollLayout mScrollLayout;

    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress >= 0) {
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
                mScrollLayout.getBackground().setAlpha(255 - (int) precent);
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.scroll_fragment, container, false);
        mScrollLayout = view.findViewById(R.id.scroll_down_layout);
        initView();
        return view;
    }

    private void initView() {

        /**设置 setting*/
        mScrollLayout.setMinOffset(20);
        mScrollLayout.setMaxOffset((int) (ScreenUtil.getScreenHeight(getActivity()) * 0.4));
        mScrollLayout.setExitOffset(ScreenUtil.dip2px(getContext(), 20));
        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.setAllowHorizontalScroll(true);
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mScrollLayout.setToExit();

        mScrollLayout.getBackground().setAlpha(0);

    }
}
