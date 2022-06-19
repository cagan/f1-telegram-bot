package com.cagan.f1telegrambot.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
@Data
public class BotProperties {
    private String username;
    private String token;
}
