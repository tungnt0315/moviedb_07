package com.example.tung.moviedb_07.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by tung on 6/14/17.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movie.db";
    private static final String SQL_CREATE_MOVIE_ENTRIES = "CREATE TABLE "
            + MovieEntry.TABLE_NAME
            + "("
            + MovieEntry.COLUMN_ID
            + " TEXT PRIMARY KEY, "
            + MovieEntry.COLUMN_TITTLE
            + " TEXT, "
            + MovieEntry.COLUMN_POSTER_PATH
            + " TEXT, "
            + MovieEntry.COLUMN_RELEASE_DATE
            + " INTEGER, "
            + MovieEntry.COLUMN_VOTE_AVERAGE
            + " REAL, "
            + MovieEntry.COLUMN_VOTE_COUNT
            + " INTEGER )";
    private static final String SQL_DELETE_MOVIE_ENTRIES =
            "DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MOVIE_ENTRIES);
        db.execSQL(SQL_CREATE_MOVIE_ENTRIES);
    }

    /**
     * Inner class that defines the table movie contents
     */
    static final class MovieEntry implements BaseColumns {
        static final String TABLE_NAME = "movie";
        static final String COLUMN_ID = "id";
        static final String COLUMN_TITTLE = "tittle";
        static final String COLUMN_POSTER_PATH = "poster";
        static final String COLUMN_RELEASE_DATE = "release_date";
        static final String COLUMN_VOTE_AVERAGE = "vote_average";
        static final String COLUMN_VOTE_COUNT = "vote_count";
    }
}
