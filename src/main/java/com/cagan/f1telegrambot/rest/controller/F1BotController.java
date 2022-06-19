package com.cagan.f1telegrambot.rest.controller;

import com.cagan.f1telegrambot.dto.RacingStatsResponse;
import com.cagan.f1telegrambot.service.F1StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class F1BotController {
    private final F1StatusService f1StatusService;

    @Autowired
    public F1BotController(F1StatusService f1StatusService) {
        this.f1StatusService = f1StatusService;
    }

    @GetMapping("/drivers/positions")
    public ResponseEntity<List<RacingStatsResponse>> getCurrentPositions() {

        return ResponseEntity.ok(f1StatusService.getCurrentStats());
    }
}
