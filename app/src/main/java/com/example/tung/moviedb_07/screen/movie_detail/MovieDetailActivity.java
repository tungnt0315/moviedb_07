package com.example.tung.moviedb_07.screen.movie_detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.databinding.ActivityMovieDetailBinding;
import com.example.tung.moviedb_07.utils.Constant;

import static com.example.tung.moviedb_07.utils.Constant.BUNDLE_FAVORITE;
import static com.example.tung.moviedb_07.utils.Constant.BUNDLE_MOVIE;

/**
 * Created by tung on 6/12/17.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        Bundle bundle = getIntent().getExtras();
        Movie movie = bundle.getParcelable(BUNDLE_MOVIE);
        boolean isFavorite = bundle.getBoolean(BUNDLE_FAVORITE, false);
        mViewModel = new MovieDetailViewModel(movie, isFavorite);
        binding.setViewModel(mViewModel);
    }
}