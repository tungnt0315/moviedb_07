package com.example.tung.moviedb_07.data.source.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.source.MovieDataSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/14/17.
 */

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private MovieDbHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public MovieLocalDataSource(@NonNull Context context) {
        mDbHelper = new MovieDbHelper(context);
    }

    @Override
    public Observable<Void> addMovie(final Movie movie) {
        mDatabase = mDbHelper.getWritableDatabase();
        return Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                ContentValues values = new ContentValues();
                values.put(MovieDbHelper.MovieEntry.COLUMN_ID, movie.getId());
                values.put(MovieDbHelper.MovieEntry.COLUMN_TITTLE, movie.getTitle());
                values.put(MovieDbHelper.MovieEntry.COLUMN_RELEASE_DATE,
                        movie.getReleaseDate().substring(0, 3));
                mDatabase.insert(MovieDbHelper.MovieEntry.TABLE_NAME, null, values);
                mDatabase.close();
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<Void> deleteMovie(final Movie movie) {
        mDatabase = mDbHelper.getWritableDatabase();
        return Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                final String selection = MovieDbHelper.MovieEntry.COLUMN_ID + " = ";
                final String[] selectionArgs = { String.valueOf(movie.getId()) };
                mDatabase.delete(MovieDbHelper.MovieEntry.TABLE_NAME, selection, selectionArgs);
                mDatabase.close();
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
                List<Movie> movies = new ArrayList<Movie>();
                String[] selectionArgs = { "20", String.valueOf(20 * (page - 1)) };
                Cursor cursor = mDatabase.rawQuery("SELECT * FROM "
                        + MovieDbHelper.MovieEntry.TABLE_NAME
                        + " LIMIT ? OFFSET ?", selectionArgs);
                if (cursor != null && cursor.moveToFirst()) {
                    movies.clear();
                    do {
                        Movie movie = new Movie();
                        movie.setId(cursor.getInt(
                                cursor.getColumnIndex(MovieDbHelper.MovieEntry.COLUMN_ID)));
                        movie.setTitle(cursor.getString(
                                cursor.getColumnIndex(MovieDbHelper.MovieEntry.COLUMN_TITTLE)));
                        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(
                                MovieDbHelper.MovieEntry.COLUMN_RELEASE_DATE)));
                        movies.add(movie);
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                e.onNext(movies);
                mDatabase.close();
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
                final String selection = MovieDbHelper.MovieEntry.COLUMN_ID + " = ";
                final String[] selectionArgs = { String.valueOf(movie.getId()) };
                Cursor cursor =
                        mDatabase.query(MovieDbHelper.MovieEntry.TABLE_NAME, null, selection,
                                selectionArgs, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    isFavorite = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                e.onNext(isFavorite);
                mDatabase.close();
                e.onComplete();
            }
        });
    }
}
