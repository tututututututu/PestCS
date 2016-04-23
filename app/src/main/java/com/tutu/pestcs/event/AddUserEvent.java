package com.tutu.pestcs.event;

import com.tutu.pestcs.bean.User;

/**
 * Created by tutu on 16/4/23.
 */
public class AddUserEvent extends BaseEvent {
	User user;

	public AddUserEvent(User user) {
		this.user = user;
	}

	public AddUserEvent() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
