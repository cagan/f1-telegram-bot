package com.cagan.f1telegrambot.bot;

import com.cagan.f1telegrambot.dto.RacingStatsResponse;
import com.cagan.f1telegrambot.service.F1StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class F1DevBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;
    private final F1StatusService statusService;

    public F1DevBot(String botUsername, String botToken, F1StatusService statusService) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.statusService = statusService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            String input = message.getText();

            StringBuilder stringBuilder = new StringBuilder();
            if ("/drivers".equals(input)) {
                List<RacingStatsResponse> list = statusService.getCurrentStats();
                String html = statusService.getStatusHtml();

                for (RacingStatsResponse response: list) {
                        stringBuilder.append(response.getPosition() + ". " + response.getDriverName() + "(" + response.getDriverNumber() + ")" + " - " + response.getTeam() + "\n");
                }

//                ObjectMapper om = new ObjectMapper();
                //                    String json = om.writer().writeValueAsString(list);
//                    message.deserializeResponse(json);
//                message.enableHtml(true);
//                    message.setText("<pre>Hello World</pre>");
                message.setText(stringBuilder.toString());
            }

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
