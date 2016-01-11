package me.yokeyword.rxapi.spf.field;

import android.content.SharedPreferences;

import me.yokeyword.rxapi.spf.BaseSpfField;


/**
 * Created by YoKeyword on 16/1/8.
 */
public class BooleanSpfField extends BaseSpfField<Boolean> {

    public BooleanSpfField(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    public Boolean get(Boolean defaultValue) {
        if (defaultValue == null) {
            defaultValue = false;
        }
        return _sharedPreferences.getBoolean(_key, defaultValue);
    }

    @Override
    public void put(Boolean value) {
        apply(edit().putBoolean(_key, value));
    }
}
