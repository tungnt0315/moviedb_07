package com.example.tung.moviedb_07.utils.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import com.bumptech.glide.Glide;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Cast;
import com.example.tung.moviedb_07.data.model.Crew;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.screen.main.MainViewPagerAdapter;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.greenfrvr.hashtagview.HashtagView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public final class BindingUtils {

    private static final String GOOGLE_DEVELOPER_KEY = "AIzaSyA03az4MQbHIO8SIwMfPFWSDYICUKDwdaM";

    private BindingUtils() {
    }

    @BindingAdapter("ViewPagerAdapter")
    public static void setViewPagerAdapter(final ViewPager viewPager,
            MainViewPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("OnPageChangeListener")
    public static void setOnPageChangeListener(final ViewPager viewPager,
            final TabLayout tabLayout) {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @BindingAdapter("UpWithViewPage")
    public static void setUpWithViewPage(final TabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("ImageUrl")
    public static void setImageUrl(final ImageView imageView, String imagePath) {
        Glide.with(imageView.getContext())
                .load(Constant.IMAGE_URL_ROOT + imagePath)
                .into(imageView);
    }

    @BindingAdapter("ImageFavorite")
    public static void setImageFavorite(final ImageView imageView, boolean isFavorite) {
        if (isFavorite) {
            imageView.setImageResource(R.drawable.icon_favorite_2);
        } else {
            imageView.setImageResource(R.drawable.icon_favorite_1);
        }
    }

    @BindingAdapter(value = { "Navigator", "VideoKey" })
    public static void setYoutubePlayer(final FrameLayout frameLayout, Navigator navigator,
            final String videoKey) {
        YouTubePlayerSupportFragment youTubePlayerFragment =
                YouTubePlayerSupportFragment.newInstance();
        youTubePlayerFragment.initialize(GOOGLE_DEVELOPER_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b) {
                        if (!b) {
                            youTubePlayer.cueVideo(videoKey);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                            YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
        navigator.replaceYoutubeFragment(frameLayout.getId(), youTubePlayerFragment);
    }

    @BindingAdapter(value = { "Director", "TagsClickListener" })
    public static void setHashtagDirector(final HashtagView hashtagView, Crew director,
            HashtagView.TagsClickListener tagsClickListener) {
        List<Crew> directors = new ArrayList<>();
        if (director.getName() == null) {
            director.setName("---");
        }
        directors.add(director);
        hashtagView.setData(directors, new HashtagView.DataTransform<Crew>() {
            @Override
            public CharSequence prepare(Crew item) {
                String label = item.getName();
                return new SpannableString(label);
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }

    @BindingAdapter(value = { "Casts", "TagsClickListener" })
    public static void setHashtagCast(final HashtagView hashtagView, List<Cast> casts,
            HashtagView.TagsClickListener tagsClickListener) {
        hashtagView.setData(casts, new HashtagView.DataTransform<Cast>() {
            @Override
            public CharSequence prepare(Cast item) {
                String label = item.getName();
                return new SpannableString(label);
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }

    @BindingAdapter(value = { "Genres", "TagsClickListener" })
    public static void setHashtagDirector(final HashtagView hashtagView, List<Genre> genres,
            HashtagView.TagsClickListener tagsClickListener) {
        hashtagView.setData(genres, new HashtagView.DataTransform<Genre>() {
            @Override
            public CharSequence prepare(Genre item) {
                String label = item.getName();
                return new SpannableString(label);
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }

    @BindingAdapter(value = { "Visibility", "RecyclerGetPosition" }, requireAll = false)
    public static void setVisibility(final View view, boolean isVisible,
            RecyclerView recyclerGetPosition) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
            if (recyclerGetPosition != null) {
                LinearLayoutManager linearLayoutManager =
                        (LinearLayoutManager) recyclerGetPosition.getLayoutManager();
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                ((RecyclerView) view).scrollToPosition(position);
            }
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("OnItemClickListener")
    public static void setOnItemClickListener(final Spinner spinner,
            AdapterView.OnItemSelectedListener onItemSelectedListener) {
        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    @BindingAdapter("OnScrollListener")
    public static void setOnScrollListener(final RecyclerView recycler,
            RecyclerView.OnScrollListener onScrollListener) {
        recycler.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter("ScrollToPosition")
    public static void scrollRecyclerViewToPosition(final RecyclerView recycler, int position) {
        recycler.scrollToPosition(position);
    }
}
