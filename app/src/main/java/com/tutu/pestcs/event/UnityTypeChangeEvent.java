package com.tutu.pestcs.event;

/**
 * Created by 47066 on 2016/6/27.
 */
public class UnityTypeChangeEvent extends BaseEvent {
    private String type;

    public UnityTypeChangeEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
