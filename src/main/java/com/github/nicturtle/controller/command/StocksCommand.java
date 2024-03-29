package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StocksCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    MaterialStocks materialStocks;
    public final static String START_MESSAGE = MaterialStocks.getTotalQuantityOfStock();

    public StocksCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}
