package com.goodtimes.events;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.math.BigInteger;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class GoodtimeEvent {

    @Id
    private String id;
    private String name;
    private String description;
    private String userId;

    private GoodtimeEvent() {}

    public GoodtimeEvent createAClone() {
        return new GoodtimeEvent(
                id,
                name,
                description,
                userId);
    }

}