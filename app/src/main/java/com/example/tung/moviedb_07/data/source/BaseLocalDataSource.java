package com.example.tung.moviedb_07.data.source;

/**
 * Created by tung on 6/13/17.
 */

public interface BaseLocalDataSource {
    void openTransaction();

    void closeTransaction();

    void openReadTransaction();
}
