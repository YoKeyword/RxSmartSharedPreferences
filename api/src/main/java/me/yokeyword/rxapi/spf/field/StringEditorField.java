package me.yokeyword.rxapi.spf.field;

import me.yokeyword.rxapi.spf.BaseEditorField;
import me.yokeyword.rxapi.spf.EditorHelper;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class StringEditorField<E extends EditorHelper> extends BaseEditorField<String, E> {

    public StringEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(String value) {
        _editorHelper.getEditor().putString(_key, value);
        return _editorHelper;
    }
}
