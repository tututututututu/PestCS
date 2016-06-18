package com.tutu.pestcs.event;

/**
 * Created by tutu on 16/4/23.
 */
public class ModifyModeEvent extends BaseEvent {
    private String unitycode;

    public ModifyModeEvent(String unitycode) {
        this.unitycode = unitycode;
    }

    public ModifyModeEvent() {
    }

    public String getUnitycode() {
        return unitycode;
    }

    public void setUnitycode(String unitycode) {
        this.unitycode = unitycode;
    }
}
