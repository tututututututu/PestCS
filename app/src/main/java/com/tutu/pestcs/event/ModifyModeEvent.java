package com.tutu.pestcs.event;

/**
 * Created by tutu on 16/4/23.
 */
public class ModifyModeEvent extends BaseEvent {
    private String unitycode;
    private boolean editable;

    public ModifyModeEvent(String unitycode,boolean editable) {
        this.unitycode = unitycode;
        this.editable = editable;
    }

    public ModifyModeEvent() {
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getUnitycode() {
        return unitycode;
    }

    public void setUnitycode(String unitycode) {
        this.unitycode = unitycode;
    }
}
