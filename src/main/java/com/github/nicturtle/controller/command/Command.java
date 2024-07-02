package com.github.nicturtle.controller.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

public interface Command {
    void execute(Update update);
}