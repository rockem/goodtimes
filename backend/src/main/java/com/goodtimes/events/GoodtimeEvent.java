package com.goodtimes.events;

import lombok.*;

import java.math.BigInteger;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class GoodtimeEvent {

    private BigInteger id;
    private String name;
    private String description;

    private GoodtimeEvent() {}

}