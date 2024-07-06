package com.github.nicturtle.controller.command;

import com.github.nicturtle.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class AddMaterialCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String PRESENTED_MESSAGE = "Какой новый материал добавить?";

    public AddMaterialCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

    @Override
    public void execute(Update update) {
        inlineKeyboardButton1.setText("добавить новый воск");
        inlineKeyboardButton1.setCallbackData("addNewWax");
        inlineKeyboardButton2.setText("добавить новые стаканы");
        inlineKeyboardButton2.setCallbackData("addNewGlass");
        inlineKeyboardButton3.setText("добавить новое аромамасло");
        inlineKeyboardButton3.setCallbackData("addNewOil");
        inlineKeyboardButton4.setText("добавить новые фитили");
        inlineKeyboardButton4.setCallbackData("addNewWicks");

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
        sendBotMessageService.sendInlineKeyboardMessage(update.getMessage().getChatId(), PRESENTED_MESSAGE, inlineKeyboardMarkup);

        String callbackData = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        switch (callbackData) {
            case "addNewWax":
                sendBotMessageService.sendMessage(chatId, "Введите новый тип воска и его количество (например, 'Соевый воск 100 кг')");
                break;
            case "addNewGlass":
                sendBotMessageService.sendMessage(chatId, "Введите новый тип стакана и его количество");
                break;
            case "addNewOil":
                sendBotMessageService.sendMessage(chatId, "Введите новый тип аромамасла и его количество");
                break;
            case "addNewWicks":
                sendBotMessageService.sendMessage(chatId, "Введите новый тип фитиля и его количество");
                break;
            default:
                sendBotMessageService.sendMessage(chatId, "Неизвестная команда");
                break;
        }
    }
}
