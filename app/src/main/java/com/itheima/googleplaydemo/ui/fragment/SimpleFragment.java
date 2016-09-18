package com.itheima.googleplaydemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/14 10:08
 * 描述： TODO
 */
public class SimpleFragment extends Fragment{

    private static final String ARG_POSITION = "position";

    private int position;

    public static SimpleFragment newInstance(int position) {
        SimpleFragment f = new SimpleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_simple, null);
        TextView tv = (TextView) root.findViewById(R.id.tv);
        position = getArguments().getInt(ARG_POSITION);
        tv.setText(String.valueOf(position));
        return root;
    }
}
