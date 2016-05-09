package com.tutu.pestcs.event;

import com.tutu.pestcs.bean.User;

/**
 * Created by tutu on 16/4/23.
 */
public class UserEidteEvent extends BaseEvent {
	User user;

	public UserEidteEvent(User user) {
		this.user = user;
	}

	public UserEidteEvent() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
