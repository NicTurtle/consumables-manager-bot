package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RestockCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String RESTOCK_MESSAGE = "PRESENTED_MESSAGE";

    public RestockCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), RESTOCK_MESSAGE);
    }
}
