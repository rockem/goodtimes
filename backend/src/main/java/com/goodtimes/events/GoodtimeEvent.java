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
    private BigInteger id;
    private String name;
    private String description;
    private BigInteger userId;

    private GoodtimeEvent() {}

    public static GoodtimeEvent createFrom(GoodtimeEvent event) {
        return new GoodtimeEvent(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getUserId());
    }

}