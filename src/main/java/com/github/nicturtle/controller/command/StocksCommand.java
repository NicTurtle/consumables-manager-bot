package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.TelegramBotConfig;
import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StocksCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    MaterialStocks materialStocks;
    public final static String START_MESSAGE = "Это меню StocksCommand\n" + "потом здесь будет функционал";

// пока я хз как брать данные из класса материалы не создавая новый объект
//            "В наличии:" +
//            "\nВоска - " + waxQuantity + " мг" +
//            "\nАрома масел - " + aromaOilQuantity + " мг" +
//            "\n + Черное море - " + blackSeaAromaOilQuantity + " мг" +
//            "\n + Манго - " + mangoAromaOilQuantity + " мг" +
//            "\n + Лемон - " + lemonAromaOilQuantity + " мг" +
//            "\nФитилей - " + wickQuantity + " шт" +
//            "\n + Нить - " + threadWickQuantity + " шт" +
//            "\n + Стабильный фитиль - " + stabilioWickQuantity + " шт" +
//            "\n + Деревянный - " + woodWickQuantity + " шт";

    public StocksCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
