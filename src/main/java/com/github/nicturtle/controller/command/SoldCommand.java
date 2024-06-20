package com.github.nicturtle.controller.command;

import com.github.nicturtle.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SoldCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String SOLD_MESSAGE = "SOLD_MESSAGE";

    public SoldCommand (SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    //TODO: Add bottoms
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), SOLD_MESSAGE);
    }
}
