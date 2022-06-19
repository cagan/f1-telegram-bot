package com.cagan.f1telegrambot.config;

import com.cagan.f1telegrambot.bot.F1DevBot;
import com.cagan.f1telegrambot.service.F1StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {
    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private F1StatusService f1StatusService;

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        TelegramBotsApi botsApi = null;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new F1DevBot(botUsername, botToken, f1StatusService));
            return botsApi;
        } catch (TelegramApiException exception) {
            exception.printStackTrace();
        }
        return botsApi;
    }
}
