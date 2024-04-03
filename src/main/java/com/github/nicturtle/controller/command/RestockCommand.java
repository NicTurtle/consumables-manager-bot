package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RestockCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String RESTOCK_MESSAGE =
            "В наличии:" +
            "\nВоска - " + MaterialStocks.waxQuantity + " мг" +
            "\nАрома масел - " + MaterialStocks.aromaOilQuantity + " мг" +
            "\n + Черное море - " + MaterialStocks.blackSeaAromaOilQuantity + " мг" +
            "\n + Манго - " + MaterialStocks.mangoAromaOilQuantity + " мг" +
            "\n + Лемон - " + MaterialStocks.lemonAromaOilQuantity + " мг" +
            "\nФитилей - " + MaterialStocks.wickQuantity + " шт" +
            "\n + Нить - " + MaterialStocks.threadWickQuantity + " шт" +
            "\n + Стабильный фитиль - " + MaterialStocks.stabilioWickQuantity + " шт" +
            "\n + Деревянный - " + MaterialStocks.woodWickQuantity + " шт";

    public RestockCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

    @Override
    public void execute(Update update) {
        inlineKeyboardButton1.setText("добавить черное море");
        inlineKeyboardButton1.setCallbackData("addBlackSeaOil");
        inlineKeyboardButton2.setText("добавить манго");
        inlineKeyboardButton2.setCallbackData("addMangoOil");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendBotMessageService.sendInlineKeyboardMessage(update.getMessage().getChatId(), RESTOCK_MESSAGE, inlineKeyboardMarkup);
    }
}
