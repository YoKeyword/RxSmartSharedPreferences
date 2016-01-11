package me.yokeyword.rxapi.spf.field;


import me.yokeyword.rxapi.spf.BaseEditorField;
import me.yokeyword.rxapi.spf.EditorHelper;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class BooleanEditorField<E extends EditorHelper> extends BaseEditorField<Boolean, E> {

    public BooleanEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Boolean value) {
        _editorHelper.getEditor().putBoolean(_key, value);
        return _editorHelper;
    }
}
