package me.yokeyword.rxapi.spf.field;

import me.yokeyword.rxapi.spf.BaseEditorField;
import me.yokeyword.rxapi.spf.EditorHelper;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class FloatEditorField<E extends EditorHelper> extends BaseEditorField<Float, E> {

    public FloatEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Float value) {
        _editorHelper.getEditor().putFloat(_key, value);
        return _editorHelper;
    }
}
