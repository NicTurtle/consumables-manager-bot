package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PresentedCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String PRESENTED_MESSAGE = "PRESENTED_MESSAGE";

    public PresentedCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), PRESENTED_MESSAGE);
    }
}
