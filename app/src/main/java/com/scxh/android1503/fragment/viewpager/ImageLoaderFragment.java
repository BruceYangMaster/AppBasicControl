package com.scxh.android1503.fragment.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scxh.android1503.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageLoaderFragment extends Fragment {
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private String url;

    public static ImageLoaderFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("URL", url);
        ImageLoaderFragment fragment = new ImageLoaderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments() != null ? getArguments().getString("URL") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_loader_layout, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imageloader_img);
        mProgressBar = (ProgressBar) v.findViewById(R.id.imageloader_bar);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProgressBar.setVisibility(View.VISIBLE);

        Picasso.with(getActivity()).load(url).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), "加载图片出错", Toast.LENGTH_SHORT).show();
            }
        });

       /* AsyncMemoryFileCacheImageLoader.getInstance(getActivity()).loadBitmap(getResources(), url, mImageView,
                new AsyncMemoryFileCacheImageLoader.Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getActivity(), "加载图片出错", Toast.LENGTH_SHORT).show();
                    }

                });*/
    }
}
