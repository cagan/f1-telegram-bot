package com.cagan.f1telegrambot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RacingStatsResponse {
    @JsonProperty("driver_name")
    private String driverName;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("driver_number")
    private Integer driverNumber;

    @JsonProperty("brand")
    private String team;

    @JsonProperty("time")
    private String time;
}
