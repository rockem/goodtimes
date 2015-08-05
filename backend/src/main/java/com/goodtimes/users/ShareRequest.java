package com.goodtimes.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ShareRequest {

    private String email;
    private String eventId;
}
