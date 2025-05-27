package com.davi.restaurant_burguer.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static LocalDateTime setTimeOut(Long minutes){
        ZonedDateTime agora = getZonedDateTime();
        ZonedDateTime futuro = agora.plusMinutes(minutes);

        return futuro.toLocalDateTime();
    }

    public static ZonedDateTime getZonedDateTime() {
        ZoneId zoneBr = ZoneId.of("America/Sao_Paulo");
        return ZonedDateTime.now(zoneBr);
    }
}
