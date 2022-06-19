package com.cagan.f1telegrambot;

import com.cagan.f1telegrambot.bot.BotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BotProperties.class)
public class F1TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(F1TelegramBotApplication.class, args);
    }
}
