package com.example.tung.moviedb_07.data.source.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import com.example.tung.moviedb_07.data.source.MovieDataSource;
import com.example.tung.moviedb_07.data.source.local.sqlite.MovieDbHelper.MovieEntry;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/14/17.
 */

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private final MovieDbHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public MovieLocalDataSource(@NonNull Context context) {
        mDbHelper = MovieDbHelper.getInstance(context);
    }

    @Override
    public Observable<Boolean> addMovie(final Movie movie) {
        mDatabase = mDbHelper.getWritableDatabase();
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                Boolean isSuccess = false;
                ContentValues values = new ContentValues();
                values.put(MovieEntry.COLUMN_ID, movie.getId());
                values.put(MovieEntry.COLUMN_TITTLE, movie.getTitle());
                values.put(MovieEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
                values.put(MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
                values.put(MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
                values.put(MovieEntry.COLUMN_VOTE_COUNT, movie.getVoteCount());
                long result = mDatabase.insert(MovieEntry.TABLE_NAME, null, values);
                if (result != -1) {
                    isSuccess = true;
                }
                e.onNext(isSuccess);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteMovie(final Movie movie) {
        mDatabase = mDbHelper.getWritableDatabase();
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                Boolean isSuccess = false;
                final String selection = MovieEntry.COLUMN_ID + " = ?";
                final String[] selectionArgs = { String.valueOf(movie.getId()) };
                int result = mDatabase.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                if (result != 0) {
                    isSuccess = true;
                }
                e.onNext(isSuccess);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Movie>> getMovies(final int page) {
        mDatabase = mDbHelper.getReadableDatabase();
        return Observable.create(new ObservableOnSubscribe<List<Movie>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Movie>> e) throws Exception {
                String[] selectionArgs = { "20", String.valueOf(20 * (page - 1)) };
                Cursor cursor = mDatabase.rawQuery(
                        "SELECT * FROM " + MovieEntry.TABLE_NAME + " LIMIT ? OFFSET ?",
                        selectionArgs);
                List<Movie> movies = getMoviesFromCursor(cursor);
                if (cursor != null) {
                    cursor.close();
                }
                e.onNext(movies);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<MovieList> getMovieList() {
        mDatabase = mDbHelper.getReadableDatabase();
        return Observable.create(new ObservableOnSubscribe<MovieList>() {
            @Override
            public void subscribe(ObservableEmitter<MovieList> e) throws Exception {
                MovieList movieList = new MovieList();

                String[] selectionArgs = { "20" };
                Cursor cursor =
                        mDatabase.rawQuery("SELECT * FROM " + MovieEntry.TABLE_NAME + " LIMIT ?",
                                selectionArgs);
                List<Movie> movies = getMoviesFromCursor(cursor);
                movieList.setMovies(movies);
                int totalMovies = getTotalmovies();
                int totalPages = totalMovies / 20;
                if (totalMovies % 20 != 0) {
                    totalPages++;
                }
                movieList.setTotalPages(totalPages);
                if (cursor != null) {
                    cursor.close();
                }
                e.onNext(movieList);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> isFavoriteMovie(final Movie movie) {
        mDatabase = mDbHelper.getReadableDatabase();
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                Boolean isFavorite = false;
                final String selection = MovieEntry.COLUMN_ID + " = ?";
                final String[] selectionArgs = { String.valueOf(movie.getId()) };
                Cursor cursor =
                        mDatabase.query(MovieEntry.TABLE_NAME, null, selection, selectionArgs, null,
                                null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    isFavorite = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                e.onNext(isFavorite);
                e.onComplete();
            }
        });
    }

    private List<Movie> getMoviesFromCursor(Cursor cursor) {
        List<Movie> movies = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(MovieEntry.COLUMN_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_TITTLE)));
                movie.setPosterPath(
                        cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_POSTER_PATH)));
                movie.setReleaseDate(
                        cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE)));
                movie.setVoteAverage(
                        cursor.getDouble(cursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)));
                movie.setVoteCount(
                        cursor.getInt(cursor.getColumnIndex(MovieEntry.COLUMN_VOTE_COUNT)));
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        return movies;
    }

    private int getTotalmovies() {
        mDatabase = mDbHelper.getReadableDatabase();
        int count;
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(1) FROM " + MovieEntry.TABLE_NAME, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }
}
