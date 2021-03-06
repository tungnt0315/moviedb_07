package com.example.tung.moviedb_07.screen;

import android.databinding.BaseObservable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tung on 6/8/17.
 */

public class BaseViewModel extends BaseObservable {

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void startDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void onStop() {
        mCompositeDisposable.clear();
    }
}
