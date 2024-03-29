package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StocksCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    MaterialStocks materialStocks;
    public final static String START_MESSAGE =
            "В наличии:" +
            "\nВоска - " + MaterialStocks.waxQuantity + " мг" +
            "\nАрома масел - " + MaterialStocks.aromaOilQuantity + " мг" +
            "\n + Черное море - " + MaterialStocks.blackSeaAromaOilQuantity + " мг" +
            "\n + Манго - " + MaterialStocks.mangoAromaOilQuantity + " мг" +
            "\n + Лемон - " + MaterialStocks.lemonAromaOilQuantity + " мг" +
            "\nФитилей - " + MaterialStocks.wickQuantity + " шт" +
            "\n + Нить - " + MaterialStocks.threadWickQuantity + " шт" +
            "\n + Стабильный фитиль - " + MaterialStocks.stabilioWickQuantity + " шт" +
            "\n + Деревянный - " + MaterialStocks.woodWickQuantity + " шт";;

    public StocksCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}
