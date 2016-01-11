package me.yokeyword.rxapi.spf.field;

import me.yokeyword.rxapi.spf.BaseEditorField;
import me.yokeyword.rxapi.spf.EditorHelper;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class IntEditorField<E extends EditorHelper> extends BaseEditorField<Integer, E> {

    public IntEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Integer value) {
        _editorHelper.getEditor().putInt(_key, value);
        return _editorHelper;
    }
}
