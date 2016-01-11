package me.yokeyword.rxapi.spf;

import android.content.SharedPreferences;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by YoKeyword on 16/1/8.
 */
public abstract class BaseSpfField<T> {
    protected final SharedPreferences _sharedPreferences;
    protected final String _key;

    public BaseSpfField(SharedPreferences sharedPreferences, String key) {
        this._sharedPreferences = sharedPreferences;
        this._key = key;
    }

    public final boolean exists() {
        return _sharedPreferences.contains(_key);
    }

    public final void remove() {
        apply(edit().remove(_key));
    }

    public final String key() {
        return this._key;
    }

    public final T get() {
        return get(null);
    }

    public abstract T get(T defaultValue);

    public abstract void put(T value);

    protected SharedPreferences.Editor edit() {
        return _sharedPreferences.edit();
    }

    protected final void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }

    /**
     * Rx部分 转为Observable
     */
    public final Observable<T> asObservable() {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(get());

                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Rx部分 转为Action1
     */
    public final Action1<T> asAction() {
        return new Action1<T>() {
            @Override
            public void call(T t) {
                put(t);
            }
        };
    }
}
