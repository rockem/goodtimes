package com.goodtimes.users;

import lombok.Data;

@Data
public class UserEvent {

    private String id;
    private String userId;
    private String eventId;

    public UserEvent(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
