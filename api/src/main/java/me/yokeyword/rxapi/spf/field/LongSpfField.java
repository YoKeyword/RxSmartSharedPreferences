package me.yokeyword.rxapi.spf.field;

import android.content.SharedPreferences;

import me.yokeyword.rxapi.spf.BaseSpfField;


/**
 * Created by YoKeyword on 16/1/8.
 */
public class LongSpfField extends BaseSpfField<Long> {

    public LongSpfField(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    public Long get(Long defaultValue) {
        if (defaultValue == null) {
            defaultValue = 0L;
        }
        return _sharedPreferences.getLong(_key, defaultValue);
    }

    @Override
    public void put(Long value) {
        apply(edit().putLong(_key, value));
    }
}
