package com.github.nicturtle.controller.command;

import com.github.nicturtle.controller.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Не понимаю вас.";

    private final SendBotMessageService sendBotMessageService;
//    private final SendInlineKeyboardMessageService sendInlineKeyboardMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
//    public UnknownCommand(SendInlineKeyboardMessageService sendInlineKeyboardMessageService) {
//        this.sendInlineKeyboardMessageService = sendInlineKeyboardMessageService;
//    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), UNKNOWN_MESSAGE);
    }
}
