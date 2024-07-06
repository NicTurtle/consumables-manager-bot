package com.github.nicturtle.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface SendBotMessageService {

    void sendMessage(Long chatId, String message);
    void sendInlineKeyboardMessage (Long chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);
}