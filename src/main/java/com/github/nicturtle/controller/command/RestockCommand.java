package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RestockCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String RESTOCK_MESSAGE =
            "В наличии:" +
            "\nВоска - " + MaterialStocks.waxQuantity + " мг" + " /addWax" +
            "\nАрома масел - " + MaterialStocks.aromaOilQuantity + " мг" +
            "\n + Черное море - " + MaterialStocks.blackSeaAromaOilQuantity + " мг" + " /addBSOil" +
            "\n + Манго - " + MaterialStocks.mangoAromaOilQuantity + " мг" + " /addMangoOil" +
            "\n + Лемон - " + MaterialStocks.lemonAromaOilQuantity + " мг" + " /addLemonOil" +
            "\nФитилей - " + MaterialStocks.wickQuantity + " шт" +
            "\n + Нить - " + MaterialStocks.threadWickQuantity + " шт" + " /addThrWick" +
            "\n + Стабильный фитиль - " + MaterialStocks.stabilioWickQuantity + " шт" + " /addStabWick" +
            "\n + Деревянный - " + MaterialStocks.woodWickQuantity + " шт" + " /addWoodWick";;

    public RestockCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), RESTOCK_MESSAGE);
//        if (update.getMessage().getText().equals("addWax")) {
//            sendBotMessageService.sendMessage(update.getMessage().getChatId(), "Сколько воска добавить? (в мг)");
//            if (update.getMessage().getText().equals("[-+]?\\\\d+")){
//                MaterialStocks.waxQuantity += Integer.getInteger(update.getMessage().getText());
//            }
//        }
    }
}
