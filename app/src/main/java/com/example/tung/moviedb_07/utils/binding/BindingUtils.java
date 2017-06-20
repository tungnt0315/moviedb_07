package com.example.tung.moviedb_07.utils.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
        Glide.with(imageView.getContext())
                .load(Constant.IMAGE_URL_ROOT + imagePath)
                .into(imageView);
    }

    @BindingAdapter("ImageFavorite")
    public static void setImageFavorite(final ImageView imageView, boolean isFavorite) {
        if (isFavorite) {
            imageView.setImageResource(R.drawable.star1);
        } else {
            imageView.setImageResource(R.drawable.star0);
        }
    }

    @BindingAdapter(value = { "navigator", "videoKey" })
    public static void setYoutubePlayer(final FrameLayout frameLayout, Navigator navigator,
            final String videoKey) {
        YouTubePlayerSupportFragment youTubePlayerFragment =
                YouTubePlayerSupportFragment.newInstance();
        youTubePlayerFragment.initialize(Constant.GOOGLE_DEVELOPER_KEY,
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

    @BindingAdapter(value = { "director", "tagsClickListener" })
    public static void setHashtagDirector(final HashtagView hashtagView, Crew director,
            HashtagView.TagsClickListener tagsClickListener) {
        List<Crew> directors = new ArrayList<>();
        directors.add(director);
        hashtagView.setData(directors, new HashtagView.DataTransform<Crew>() {
            @Override
            public CharSequence prepare(Crew item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                return spannableString;
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }

    @BindingAdapter(value = { "casts", "tagsClickListener" })
    public static void setHashtagCast(final HashtagView hashtagView, List<Cast> casts,
            HashtagView.TagsClickListener tagsClickListener) {
        hashtagView.setData(casts, new HashtagView.DataTransform<Cast>() {
            @Override
            public CharSequence prepare(Cast item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                return spannableString;
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }

    @BindingAdapter(value = { "genres", "tagsClickListener" })
    public static void setHashtagDirector(final HashtagView hashtagView, List<Genre> genres,
            HashtagView.TagsClickListener tagsClickListener) {
        hashtagView.setData(genres, new HashtagView.DataTransform<Genre>() {
            @Override
            public CharSequence prepare(Genre item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                return spannableString;
            }
        });
        hashtagView.addOnTagClickListener(tagsClickListener);
    }
}
