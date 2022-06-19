package com.cagan.f1telegrambot.crawler;

import java.util.List;

public interface F1RacingCrawler {

    List<String> getDriverNames();

    List<String> getDriverNumber();

    List<String> getPosition();

    List<String> getDriverTeam();

    List<String> getDriverTime();
}
