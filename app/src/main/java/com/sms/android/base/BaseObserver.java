package com.sms.android.base;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        try {
            onDisposable(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {
        try {
            onNextObserver(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void onNextObserver(T t)throws Exception;
    public abstract void onDisposable(Disposable d)throws Exception;
}
