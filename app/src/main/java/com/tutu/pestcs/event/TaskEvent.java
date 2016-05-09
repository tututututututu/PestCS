package com.tutu.pestcs.event;

import com.tutu.pestcs.bean.TaskBean;

/**
 * Created by tutu on 16/4/23.
 */
public class TaskEvent extends BaseEvent {
    TaskBean task;

    public TaskEvent(TaskBean task) {
        this.task = task;
    }

    public TaskBean getTask() {
        return task;
    }

    public void setTask(TaskBean task) {
        this.task = task;
    }
}
