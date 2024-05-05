package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RestockCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    private String getRestockMessage() {
        return             "В наличии:" +
                "\nВоска - " + MaterialStocks.waxQuantity + " мг" +
                "\nАрома масел - " + MaterialStocks.aromaOilQuantity + " мг" +
                "\n + Черное море - " + MaterialStocks.blackSeaAromaOilQuantity + " мг" +
                "\n + Манго - " + MaterialStocks.mangoAromaOilQuantity + " мг" +
                "\n + Лемон - " + MaterialStocks.lemonAromaOilQuantity + " мг" +
                "\nФитилей - " + MaterialStocks.wickQuantity + " шт" +
                "\n + Нить - " + MaterialStocks.threadWickQuantity + " шт" +
                "\n + Стабильный фитиль - " + MaterialStocks.stabilioWickQuantity + " шт" +
                "\n + Деревянный - " + MaterialStocks.woodWickQuantity + " шт";
    }

    //TODO:
    private String getAddOilMessage() {
        return "";
    }


    public RestockCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

    @Override
    public void execute(Update update) {
        inlineKeyboardButton1.setText("добавить воск");
        inlineKeyboardButton1.setCallbackData("addWax");
        inlineKeyboardButton2.setText("добавить стаканы");
        inlineKeyboardButton2.setCallbackData("addGlass");
        inlineKeyboardButton3.setText("добавить аромамасло");
        inlineKeyboardButton3.setCallbackData("addOil");
        inlineKeyboardButton4.setText("добавить фитили");
        inlineKeyboardButton4.setCallbackData("addWicks");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendBotMessageService.sendInlineKeyboardMessage(update.getMessage().getChatId(), getRestockMessage(), inlineKeyboardMarkup);
    }
}
