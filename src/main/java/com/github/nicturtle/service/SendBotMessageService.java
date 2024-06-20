package com.github.nicturtle.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface SendBotMessageService {

    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(Long chatId, String message);
    void sendInlineKeyboardMessage (Long chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);
}