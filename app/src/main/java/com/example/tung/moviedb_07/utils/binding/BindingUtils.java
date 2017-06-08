package com.example.tung.moviedb_07.utils.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.tung.demomovie.screen.main.MainViewPagerAdapter;
import com.example.tung.demomovie.untils.Constant;

public final class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("viewPagerAdapter")
    public static void setViewPagerAdapter(final ViewPager viewPager,
            MainViewPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("onPageChangeListener")
    public static void setOnPageChangeListener(final ViewPager viewPager,
            final TabLayout tabLayout) {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @BindingAdapter("upWithViewPage")
    public static void setUpWithViewPage(final TabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("ImageUrl")
    public static void setImageSrc(final ImageView imageView, String imagePath) {
        Glide.with(imageView.getContext()).load(Constant.IMAGE_URL_ROOT + imagePath).into(imageView);
    }
}
