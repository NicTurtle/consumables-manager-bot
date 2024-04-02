package com.github.nicturtle.controller.command.restockCommands;

import com.github.nicturtle.controller.TelegramBotConfig;
import com.github.nicturtle.controller.command.Command;
import com.github.nicturtle.controller.service.SendBotMessageService;
import com.github.nicturtle.model.MaterialStocks;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessages;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.util.regex.Pattern;

public class AddWaxCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String ADD_WAX_MESSAGE = "ADD_WAX_MESSAGE";

    public AddWaxCommand (SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), ADD_WAX_MESSAGE);

    }
}
